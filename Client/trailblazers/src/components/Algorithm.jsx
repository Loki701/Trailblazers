import React, { useState } from 'react';

const Algorithm = () => {
  // State to track the active nav item
  const [activeNav, setActiveNav] = useState('Dijkstra'); // Default active nav item

  // Content for each algorithm
  const getAlgorithmContent = (algorithm) => {
    switch (algorithm) {
      case 'Dijkstra':
        return "Dijkstra's algorithm  is an algorithm for finding the shortest paths between nodes in a weighted graph, which may represent, for example, road networks. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later. The algorithm exists in many variants. Dijkstra's original algorithm found the shortest path between two given nodes,[6] but a more common variant fixes a single node as the 'source' node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree."
      case 'DFS':
        return "Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking. Extra memory, usually a stack, is needed to keep track of the nodes discovered so far along a specified branch which helps in backtracking of the graph. A version of depth-first search was investigated in the 19th century by French mathematician Charles Pierre Trémaux as a strategy for solving mazes.";
      case 'BFS':
        return "Breadth-first search (BFS) is an algorithm for searching a tree data structure for a node that satisfies a given property. It starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level. Extra memory, usually a queue, is needed to keep track of the child nodes that were encountered but not yet explored.";
      case 'Bellman-Ford':
        return 'Bellman-Ford algorithm finds shortest paths in graphs with negative weights.';
      case 'A*':
        return "A* (pronounced 'A-star') is a graph traversal and pathfinding algorithm, which is used in many fields of computer science due to its completeness, optimality, and optimal efficiency. Given a weighted graph, a source node and a goal node, the algorithm finds the shortest path (with respect to the given weights) from source to goal. One major practical drawback is its O (b^d)space complexity, as it stores all generated nodes in memory. Thus, in practical travel-routing systems, it is generally outperformed by algorithms that can pre-process the graph to attain better performance, as well as by memory-bounded approaches; however, A* is still the best solution in many cases. Peter Hart, Nils Nilsson and Bertram Raphael of Stanford Research Institute (now SRI International) first published the algorithm in 1968.[4] It can be seen as an extension of Dijkstra's algorithm. A* achieves better performance by using heuristics to guide its search. Compared to Dijkstra's algorithm, the A* algorithm only finds the shortest path from a specified source to a specified goal, and not the shortest-path tree from a specified source to all possible goals. This is a necessary trade-off for using a specific-goal-directed heuristic. For Dijkstra's algorithm, since the entire shortest-path tree is generated, every node is a goal, and there can be no specific-goal-directed heuristic.";
      default:
        return 'Information about algorithm';
    }
  };

  return (
    <div className="site-wrap">
      <nav className="site-nav">
        <ul>
          <li className={activeNav === 'Dijkstra' ? 'active' : ''}>
            <a href="#Dijkstra" onClick={() => setActiveNav('Dijkstra')}>Dijkstra</a>
          </li>
          <li className={activeNav === 'DFS' ? 'active' : ''}>
            <a href="#DFS" onClick={() => setActiveNav('DFS')}>Depth First Search</a>
          </li>
          <li className={activeNav === 'BFS' ? 'active' : ''}>
            <a href="#BFS" onClick={() => setActiveNav('BFS')}>Breadth First Search</a>
          </li>
          <li className={activeNav === 'Bellman-Ford' ? 'active' : ''}>
            <a href="#Bellman-Ford" onClick={() => setActiveNav('Bellman-Ford')}>Bellman-Ford</a>
          </li>
          <li className={activeNav === 'A*' ? 'active' : ''}>
            <a href="#A*" onClick={() => setActiveNav('A*')}>A*</a>
          </li>
        </ul>
      </nav>

      <main>
        <header>
          <h1 className="title">{activeNav}</h1>
        </header>

        <div className="algo-content">
          <p>{getAlgorithmContent(activeNav)}</p>
        </div>
      </main>
    </div>
  );
};

export default Algorithm;
