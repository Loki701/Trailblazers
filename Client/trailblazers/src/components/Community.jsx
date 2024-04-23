import React, { useEffect, useState } from 'react';

const Community = () => {

  return (
    <div className='faq-container'>
      <div className="faq-heading">
        <h1>FAQ</h1>
      </div>
      <div className='faq-content'>
        <div>
          <details open>
            <summary>
              What is pathfinding?
            </summary>
            <div>
              Pathfinding is the process of finding the shortest or most optimal path between two points. It is commonly used in various applications such as GPS navigation, robotics, and video games. A pathfinding visualizer allows users to see how different algorithms work to find paths.
            </div>
          </details>
          <details>
            <summary>
              Which pathfinding algorithms are visualized on this website?
            </summary>
            <div>
              Our website visualizes several popular pathfinding algorithms, including:
              <br />
              <br />
              • Dijkstra's Algorithm: Finds the shortest path based on edge weights.
              <br />
              • A* Algorithm: Uses heuristics for efficient pathfinding.
              <br />
              • Breadth-First Search (BFS): Explores nodes in a broad, level-based manner.
              <br />
              • Depth-First Search (DFS): Explores as far as possible along each branch.
              <br />
              <br />
              Each algorithm has its unique characteristics, and you can experiment with them to understand how they operate.
            </div>
          </details>
          <details>
            <summary>
              How do I use the visualizer to find a path?
            </summary>
            <div>
              To use the visualizer, follow these steps:
              <br />
              <br />
              1. Set the starting and ending points on the grid.
              <br />
              2. Choose a pathfinding algorithm from the dropdown menu.
              <br />
              3. Choose the speed you want the algorithm to run from the dropdown menu.
              <br />
              4. Click the "Start" button to initiate the pathfinding process.
              <br />
              5. Watch the algorithm visualize its search for a path. The visualizer will highlight the nodes it visits and the final path if one is found.
            </div>
          </details>
          <details>
            <summary>
              What factors affect the speed and efficiency of pathfinding algorithms?
            </summary>
            <div>
              The speed and efficiency of pathfinding algorithms depend on several factors:
              <br/>
              <br/>
              • Grid size: Larger grids require more computation.
              <br/>
              • Obstacles: The presence of obstacles or barriers can affect the pathfinding process.
              <br/>
              • Algorithm type: Some algorithms are inherently faster or more efficient than others.
              <br/>
              • Heuristics: Algorithms like A* use heuristics to speed up the process.
              <br/>
              <br/>
              Experiment with different settings and algorithms to find the best solution for your use case
            </div>
          </details>
        </div>
      </div>
    </div>
  );
};

export default Community;