import React, { useState, useEffect } from "react";
import axios from "axios";

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
  const [startNodeCoords, setStartNodeCoords] = useState([Math.floor(ROWS / 2), Math.floor(COLS / 4)]);
  const [endNodeCoords, setEndNodeCoords] = useState([Math.floor(ROWS / 2), Math.floor((3 * COLS) / 4)]);
  const [grid, setGrid] = useState(createGrid());
  const [isMouseDown, setIsMouseDown] = useState(false);
  const [currentNodeState, setCurrentNodeState] = useState(null);
  const [algorithmSelector, setAlgorithmSelector] = useState("Algorithm");
  const [paceSelector, setPaceSelector] = useState("Pace");
  const [mazeSelector, setMazeSelector] = useState("Maze Type");

  const handleChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;
    if (name === "algo") {
      setAlgorithmSelector(value);
      // handleDropdownSelection( 'algorithm', value);
    } else if (name === "pace") {
      setPaceSelector(value);
      // handleDropdownSelection( 'pace', value);
    } else if (name === "maze") {
      setMazeSelector(value);
      // handleDropdownSelection( 'maze', value);
    }
  };

  const handleMouseDown = async (row, col) => {
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
        await axios.patch(
          `http://localhost:8080/maze/cells/${row}/${col}`,
          { newCellType: newCellT }
        );
      }
      catch (err) {
        console.log("error patching cell", err);
      }
    }
  };

  const handleMouseEnter = async (row, col) => {
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
        await axios.patch(
          `http://localhost:8080/maze/cells/${row}/${col}`,
          { newCellType: "start" }
        );
      } catch (err) {
        console.log("error patching cell", err);
      }
      setGrid(newGrid);
    } else if (currentNodeState === "end") {
      const updatedNode = { ...grid[row][col], isEnd: true };
      newGrid[row][col] = updatedNode;
      // setEndNodeCoords([row, col]);
      try {
        await axios.patch(
          `http://localhost:8080/maze/cells/${row}/${col}`,
          { newCellType: "finish" }
        );
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
        await axios.patch(
          `http://localhost:8080/maze/cells/${row}/${col}`,
          patchRequestBody
        );
      } catch (err) {
        console.log("error patching cell", err)
      }
      console.log("updated node")
      setGrid(newGrid);
    }
  };

  const handleMouseUp = () => {
    setIsMouseDown(false);
  };

  useEffect(() => {
    const initMaze = async () => {

      try {

        const mazeStatusResponse = await axios.get("http://localhost:8080/maze/status");

        console.log(mazeStatusResponse)
        if (mazeStatusResponse.data) {
          await axios.delete("http://localhost:8080/maze");
        }
        await axios.post(
          "http://localhost:8080/maze"
        );
        console.log("maze initialized");

        await axios.patch(
          `http://localhost:8080/maze/cells/${startNodeCoords[0]}/${startNodeCoords[1]}`,
          { newCellType: "start" }
        );
        await axios.patch(
          `http://localhost:8080/maze/cells/${endNodeCoords[0]}/${endNodeCoords[1]}`,
          { newCellType: "finish" }
        );

        console.log("start and end cells modified");

      } catch (err) {
        console.log("maze error: ", err);
      }
    }

    initMaze();
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
    console.log(l);

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
      await clearVisitedBlocks();

      // Get shortest path
      const shortestPathResponse = await axios.get(
        `http://localhost:8080/maze/shortest-path?algorithm=${algorithmSelector.toLowerCase()}`
      );
      if (shortestPathResponse.data.isCompletable === false) {
        alert("No path found!");
        return;
      }
      const visitedPath = shortestPathResponse.data.visitOrder;

      const shortestPath = shortestPathResponse.data.shortestPath;

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
          <option value="Algorithm">Algorithm</option>
          <option value="Dijkstra">Dijkstra</option>
          <option value="BFS">BFS</option>
          <option value="DFS">DFS</option>
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
          <option value={.5} >Fast</option>
        </select>

        <select
          className="dds"
          value={mazeSelector}
          name="maze"
          onChange={handleChange}
          required
        >
          <option value="Maze Type">Maze Type</option>
          <option value="None">None</option>
          {/* <option value="Recursive">Recursive</option>
          <option value="Simplex">Simplex</option> */}
        </select>
      </div>
      {/* <div style={{ color: "white" }}>
        {startNodeCoords[0]}, {startNodeCoords[1]} |
        {endNodeCoords[0]}, {endNodeCoords[1]}
      </div> */}
      <div className="main-container">
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
        <div className="card">
          <h1>Algorithm Visualizer</h1>
          <p>
            Welcome to the algorithm visualizer! This tool allows you to
            visualize the pathfinding and maze generation algorithms in action.
            To get started, select an algorithm, pace, and maze type from the
            dropdown menu and click the run button. You can also create walls by
            clicking and dragging on the grid. Enjoy!
          </p>
        </div>
      </div>
      <button className="glowing-btn" onClick={handleRun}>
        <span className="glowing-txt">
          R<span className="faulty-letter">U</span>N{" "}
        </span>
      </button>
    </div>
  );
};

export default LandingPage;
