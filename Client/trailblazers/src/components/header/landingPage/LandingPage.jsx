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
  const [grid, setGrid] = useState(createGrid());
  const [isMouseDown, setIsMouseDown] = useState(false);
  const [currentNodeState, setCurrentNodeState] = useState(null);
  // const [algorithm, setAlgorithm] = useState("Algorithm");
  // const [pace, setPace] = useState("Normal");
  // const [maze, setMaze] = useState("None");
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

  const handleMouseDown = (row, col) => {
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
    } else if (grid[row][col].isEnd) {
      setCurrentNodeState("end");
    } else {
      const updatedNode = { ...grid[row][col], isWall: !grid[row][col].isWall };
      newGrid[row][col] = updatedNode;
      setGrid(newGrid);
      setCurrentNodeState("wall");
    }
  };

  const handleMouseEnter = (row, col) => {
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
      setGrid(newGrid);
    } else if (currentNodeState === "end") {
      const updatedNode = { ...grid[row][col], isEnd: true };
      newGrid[row][col] = updatedNode;
      setGrid(newGrid);
    } else if (currentNodeState === "wall") {
      const updatedNode = { ...grid[row][col], isWall: !grid[row][col].isWall };
      newGrid[row][col] = updatedNode;
      setGrid(newGrid);
    }
  };

  const handleMouseUp = () => {
    setIsMouseDown(false);
  };

  useEffect(() => {
    document.addEventListener("mouseup", handleMouseUp);
    return () => {
      document.removeEventListener("mouseup", handleMouseUp);
    };
  }, []);

  const displayVisitedNodes = (visitedNodes) => {
    const animationSpeed = 100; // Adjust the speed of the animation
    console.log("displayVisitedNodes started");

    visitedNodes.forEach((node, index) => {
      setTimeout(() => {
        const { row, col } = node;
        console.log(row, col);
        setGrid((prevGrid) => {
          const updatedGrid = prevGrid.map((gridRow) =>
            gridRow.map((gridNode) => {
              if (gridNode.row === row && gridNode.col === col) {
                return {
                  ...gridNode,
                  isVisited: true,
                };
              }
              return gridNode;
            })
          );
          return updatedGrid;
        });
      }, index * animationSpeed);
    });
  };
  // const handleDropDownSelection = (name, value) => {
  //   if(name === "algorithm") setAlgorithm(value);
  //   else if(name === "pace") setPace(value);
  //   else if(name === "maze") setMaze(value);
  // }
  const handleRun = async () => {
    //contact server to get visited nodes
    // const coordinatesArray = [
    //   { row: 0, col: 1 },
    //   { row: 0, col: 2 },
    //   { row: 0, col: 3 },
    //   { row: 0, col: 4 },
    //   { row: 0, col: 5 },
    //   { row: 0, col: 6 },
    //   { row: 0, col: 7 },
    //   { row: 1, col: 7 },
    //   // Add more coordinates as needed
    // ];
    // if maze has ran before remove all visited nodes
    if (
      algorithmSelector === "Algorithm" ||
      paceSelector === "Pace" ||
      mazeSelector === "Maze Type"
    ) {
      alert("Please select valid values for all selectors before running.");
      return;
    }

    const dimensions = { rows: ROWS, cols: COLS };
    const sendData = {
      dimensions: dimensions,
      algorithmName: algorithmSelector,
      grid: grid,
    };
    // console.log(JSON.stringify(sendData));
    try {
      //post maze
      const response = await axios.post(
        " http://localhost:8080/run",
        JSON.stringify(sendData)
      );
      const visitedNodesFromServer = response.data.visitedNodes;
      displayVisitedNodes(visitedNodesFromServer);
    } catch (e) {
      console.error("Error fetching data from the server:", e);
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
          {/* Add more options as needed */}
        </select>

        <select
          className="dds"
          value={paceSelector}
          name="pace"
          onChange={handleChange}
          required
        >
          <option value="Slow">Pace</option>
          <option value="Slow">Slow</option>
          <option value="Normal">Normal</option>
          <option value="Fast">Fast</option>
        </select>

        <select
          className="dds"
          value={mazeSelector}
          name="maze"
          onChange={handleChange}
          required
        >
          <option value="None">Maze Type</option>
          <option value="None">None</option>
          <option value="Recursive">Recursive</option>
          <option value="Simplex">Simplex</option>
        </select>
      </div>
      <div className="main-container">
        <div className="grid">
          {grid.map((row, rowIndex) => (
            <div key={rowIndex} className="row">
              {row.map((node, colIndex) => (
                <div
                  key={colIndex}
                  className={`node ${node.isStart ? "start" : ""} ${
                    node.isEnd ? "end" : ""
                  } ${node.isWall ? "wall" : ""} ${
                    node.isVisited ? "visited" : ""
                  }`}
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
