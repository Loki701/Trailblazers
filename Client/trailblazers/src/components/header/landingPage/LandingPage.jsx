import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

const ROWS = 20;
const COLS = 30;

const createGrid = () => {
  const grid = [];
  for (let row = 0; row < ROWS; row++) {
    const currentRow = [];
    for (let col = 0; col < COLS; col++) {
      currentRow.push({
        row,
        col,
        isStart: row === Math.floor(ROWS / 2) && col === Math.floor(COLS / 4),
        isEnd:
          row === Math.floor(ROWS / 2) && col === Math.floor((3 * COLS) / 4),
        isWall: false,
        distance: Infinity,
        isVisited: false,
        previousNode: null,
      });
    }
    grid.push(currentRow);
  }
  return grid;
};

const LandingPage = () => {
  const [pathLength, setPathLength] = useState(0);
  const [runTime, setRunTime] = useState(0);
  const [grid, setGrid] = useState(createGrid());
  const [isMouseDown, setIsMouseDown] = useState(false);
  const [currentNodeState, setCurrentNodeState] = useState(null);
  const [algorithmSelector, setAlgorithmSelector] = useState("Algorithm");
  const [paceSelector, setPaceSelector] = useState("Pace");
  const [mazeSelector, setMazeSelector] = useState("Maze Type");
  const [isRunning, setIsRunning] = useState(false);
  const [text, setText] = useState("Algorithm Visualizer");
  const [info, setInfo] = useState("Welcome to the algorithm visualizer! This tool allows you to visualize the pathfinding and maze generation algorithms in action. To get started, select an algorithm, pace, and maze type from the dropdown menu and click the run button. You can also create walls by clicking and dragging on the grid. Enjoy!");

  const algoInfo = [
    { name: "Dijkstra", info: "Dijkstra's algorithm is a pathfinding algorithm that finds the shortest path between two nodes in a graph. It works by iteratively selecting the node with the smallest distance from the start node and updating the distances of its neighbors. The algorithm continues until the end node is reached, at which point the shortest path is reconstructed by backtracking through the nodes." },
    { name: "BFS", info: "Breadth-first search (BFS) is a graph traversal algorithm that explores all the neighboring nodes at the present depth before moving on to the nodes at the next depth. It is often used to find the shortest path between two nodes in an unweighted graph." },
    { name: "DFS", info: "Depth-first search (DFS) is a graph traversal algorithm that explores as far as possible along each branch before backtracking. It is often used to explore all the nodes in a graph and can be modified to find the shortest path between two nodes." },
    { name: "Bellman-Ford", info: "Bellman-Ford is a single-source shortest path algorithm that can handle negative edge weights. It works by iteratively relaxing the edges of the graph until the shortest paths are found. The algorithm detects negative cycles and returns an error if one is found." },
    { name: "A-Star", info: "A* (pronounced 'A-star') is a pathfinding algorithm that uses a heuristic to guide its search. It is often used to find the shortest path between two nodes in a graph and is more efficient than Dijkstra's algorithm for this purpose. A* uses a priority queue to select the next node to explore based on the sum of the cost to reach the node and the heuristic estimate of the cost to reach the goal." },
  ]
  const navigate = useNavigate();

  const handleLearnMoreClick = () => {
    navigate('/algorithms');
  };

  const handleChange = async (event) => {
    const value = event.target.value;
    const name = event.target.name;
    if (name === "algo") {
      setAlgorithmSelector(value);
      setText(value);
      setInfo(algoInfo.find(algo => algo.name === value).info);
      // handleDropdownSelection( 'algorithm', value);
    } else if (name === "pace") {
      setPaceSelector(value);
      // handleDropdownSelection( 'pace', value);
    } else if (name === "maze") {
      setIsRunning(false);
      if (value === "0") {
        //Delete all walls
        const updatedGrid = grid.map(row =>
          row.map(node => ({
            ...node,
            isWall: false,
            distance: Infinity,
            isVisited: false,
            previousNode: null,
          }))
        );
        setGrid(updatedGrid);
        return
      } else {
        try {
          const mazeData = await axios.get(`http://localhost:8080/maze/presets/${value}`);
          // MazeData is a 2D int array

          const updatedGrid = grid.map((row, rowIndex) =>
            row.map((node, colIndex) => {
              return {
                ...node,
                isStart: mazeData.data[rowIndex][colIndex] === 2,
                isEnd: mazeData.data[rowIndex][colIndex] === 3,
                isWall: mazeData.data[rowIndex][colIndex] === 1,
                distance: Infinity,
                isVisited: false,
                previousNode: null,
              };
            })
          );
          setGrid(updatedGrid);

        } catch (err) {
          console.log("error fetching maze", err);
        }
      }

      setMazeSelector(value);
      // handleDropdownSelection( 'maze', value);
    }
  };

  const handleMouseDown = async (row, col) => {
    if (isRunning) return;
    setIsMouseDown(true);
    const newGrid = grid.map((row) =>
      row.map((node) => {
        return {
          ...node,
        };
      })
    );

    if (grid[row][col].isStart) {
      setCurrentNodeState("start");
      // setStartNodeCoords({row,col});
    } else if (grid[row][col].isEnd) {
      setCurrentNodeState("end");
      // setEndNodeCoords({row,col});
    } else {
      const updatedNode = { ...grid[row][col], isWall: !grid[row][col].isWall };
      newGrid[row][col] = updatedNode;
      let newCellT = updatedNode.isWall ? "wall" : "empty";
      setGrid(newGrid);
      setCurrentNodeState("wall");
      try {
        // await axios.patch(
        //   `http://localhost:8080/maze/cells/${row}/${col}`,
        //   { newCellType: newCellT }
        // );
      }
      catch (err) {
        console.log("error patching cell", err);
      }
    }
  };

  const handleMouseEnter = async (row, col) => {
    if (isRunning) return;
    if (!isMouseDown) return;

    const newGrid = grid.map((row) =>
      row.map((node) => {
        return {
          ...node,
          isStart: currentNodeState === "start" ? false : node.isStart,
          isEnd: currentNodeState === "end" ? false : node.isEnd,
        };
      })
    );

    if (currentNodeState === "start") {
      const updatedNode = { ...grid[row][col], isStart: true };
      newGrid[row][col] = updatedNode;
      // setStartNodeCoords([row, col]);
      try {
        // await axios.patch(
        //   `http://localhost:8080/maze/cells/${row}/${col}`,
        //   { newCellType: "start" }
        // );
      } catch (err) {
        console.log("error patching cell", err);
      }
      setGrid(newGrid);
    } else if (currentNodeState === "end") {
      const updatedNode = { ...grid[row][col], isEnd: true };
      newGrid[row][col] = updatedNode;
      // setEndNodeCoords([row, col]);
      try {
        // await axios.patch(
        //   `http://localhost:8080/maze/cells/${row}/${col}`,
        //   { newCellType: "finish" }
        // );
      } catch (err) {
        console.log("error patching cell", err);
      }
      setGrid(newGrid);
    } else if (currentNodeState === "wall") {
      const updatedNode = { ...grid[row][col], isWall: !grid[row][col].isWall };
      newGrid[row][col] = updatedNode;
      let newCellT = updatedNode.isWall ? "wall" : "empty";
      const patchRequestBody = {
        newCellType: newCellT
      };
      try {
        // await axios.patch(
        //   `http://localhost:8080/maze/cells/${row}/${col}`,
        //   patchRequestBody
        // );
      } catch (err) {
        console.log("error patching cell", err)
      }
      console.log("updated node")
      setGrid(newGrid);
    }
  };

  const handleMouseUp = () => {
    if (isRunning) return;
    setIsMouseDown(false);
  };

  useEffect(() => {
    document.addEventListener("mouseup", handleMouseUp);
    return () => {
      document.removeEventListener("mouseup", handleMouseUp);
    };
  }, []);

  const displayVisitedNodes = async (visitedNodes, isShortestPath) => {
    const animationSpeed = 100 * paceSelector; // Adjust the speed of the animation
    console.log("displayVisitedNodes started");
    if (visitedNodes === null) {
      alert("Issue with our Server try again later!")
      return;
    }
    const l = visitedNodes.length;

    // Loop through each visited node
    for (let index = 0; index < l; index++) {
      const node = visitedNodes[index];
      if (!(index === 0 || index === l - 1)) {
        // Introduce a delay between animation steps according to the pace selector
        await new Promise((resolve) => setTimeout(resolve, animationSpeed));

        // Update the grid to mark the node as visited
        const { row, col } = node;
        setGrid((prevGrid) => {
          const updatedGrid = prevGrid.map((gridRow) =>
            gridRow.map((gridNode) => {
              if (gridNode.row === row && gridNode.col === col) {
                return {
                  ...gridNode,
                  isVisited: true,
                  isShortestPath: isShortestPath
                };
              }
              return gridNode;
            })
          );
          return updatedGrid;
        });
      }
    }
  };

  const clearVisitedBlocks = () => {
    return new Promise((resolve) => {
      const updatedGrid = grid.map(row =>
        row.map(node => ({
          ...node,
          isVisited: false, // Reset isVisited flag
        }))
      );
      setGrid(updatedGrid);
      resolve(); // Resolve the promise after updating the grid
    });
  };




  const handleRun = async () => {
    setIsRunning(!isRunning);
    if (isRunning) {
      await clearVisitedBlocks();
      setPathLength(0);
      setRunTime(0);
      return;
    }
    if (
      algorithmSelector === "Algorithm" ||
      paceSelector === "Pace"
      // mazeSelector === "Maze Type"
    ) {
      alert(
        "Please select valid values for all selectors before running."
      );
      return;
    }

    try {

      const mazeStatusResponse = await axios.get("http://localhost:8080/maze/status");

      if (mazeStatusResponse.data) {
        await axios.delete("http://localhost:8080/maze");
      }
      const newGrid = [];
      for (let row = 0; row < ROWS; row++) {
        const currentRow = [];
        for (let col = 0; col < COLS; col++) {
          if (grid[row][col].isStart) {
            currentRow.push(2);
          } else if (grid[row][col].isEnd) {
            currentRow.push(3);
          }
          else if (grid[row][col].isWall) {
            currentRow.push(1);
          }
          else {
            currentRow.push(0);
          }
        }
        newGrid.push(currentRow);
      }

      await axios.post(
        "http://localhost:8080/maze",
        { board: newGrid }
      );

      // Get shortest path
      const shortestPathResponse = await axios.get(
        `http://localhost:8080/maze/shortest-path?algorithm=${algorithmSelector.toLowerCase()}`
      );
      if (shortestPathResponse.data.isCompletable === false) {
        alert("No path found!");
        return;
      }
      setRunTime(shortestPathResponse.data.executionTimeNano);
      const visitedPath = shortestPathResponse.data.visitOrder;


      const shortestPath = shortestPathResponse.data.shortestPath;
      setPathLength(shortestPath.length - 1);

      console.log(shortestPathResponse);
      await displayVisitedNodes(visitedPath, false);
      await displayVisitedNodes(shortestPath, true);

    } catch (error) {
      console.error("Error fetching data from the server:", error);
    }
  };


  return (
    <div className="landingPage">
      <div className="selector-container">
        <select
          className="dds"
          value={algorithmSelector}
          name="algo"
          onChange={handleChange}
          required
        >
          <option value="Algorithm" >Algorithm</option>
          <option value="Dijkstra">Dijkstra</option>
          <option value="BFS">BFS</option>
          <option value="DFS">DFS</option>
          <option value="Bellman-Ford">Bellman-Ford</option>
          <option value="A-Star">A-Star</option>
        </select>

        <select
          className="dds"
          value={paceSelector}
          name="pace"
          onChange={handleChange}
          required
        >
          <option value="Pace">Pace</option>
          <option value={2}>Slow</option>
          <option value={1}>Normal</option>
          <option value={.2} >Fast</option>
        </select>

        <select
          className="dds"
          value={mazeSelector}
          name="maze"
          onChange={handleChange}
          required
        >
          <option value="Maze Type">Maze Type</option>
          <option value="0">None</option>
          <option value="1">Maze 1</option>
          <option value="2">Maze 2</option>
          <option value="3">Maze 3</option>
        </select>
      </div>
      <div className="main-container">
        <div className="main-content-container">
          <div className="grid">
            {grid.map((row, rowIndex) => (
              <div key={rowIndex} className="row">
                {row.map((node, colIndex) => (
                  <div
                    key={colIndex}
                    className={`node 
                  ${node.isWall ? "wall" : ""} 
                  ${node.isVisited ? (node.isShortestPath ? "shortest-path" : "visited") : ""}
                  ${node.isEnd ? "end" : ""} 
                  ${node.isStart ? "start" : ""} 
                  `}
                    onMouseDown={() => handleMouseDown(rowIndex, colIndex)}
                    onMouseEnter={() => handleMouseEnter(rowIndex, colIndex)}
                  ></div>
                ))}
              </div>
            ))}

          </div>
          <div className="info-container">
            <p>Time: {runTime} ns</p>
          </div>
          <div className="info-container">
            <p>Path Length: {pathLength} tiles</p>
          </div>
        </div>
        <div className="card">
          <h1>{text}</h1>
          <p>
            {info}
          </p>
          {text != "Algorithm Visualizer" ?
            (
              <button
                className="learn-more-btn"
                onClick={handleLearnMoreClick}
              >Learn More</button>
            ) : (
              <></>
            )
          }
        </div>
      </div>
      <button className="glowing-btn" onClick={handleRun}>
        {isRunning ? (
          <span className="glowing-txt ">
            CL<span className="faulty-letter">E</span>A<span className="faulty-letter">R</span>{" "}
          </span>) : (
          <span className="glowing-txt">
            R<span className="faulty-letter">U</span>N{" "}
          </span>)}
      </button>
    </div>
  );
};

export default LandingPage;
