import React, { useState } from 'react';
import DIJKSTRAIMG from '../images/djimg.png';

const Algorithm = () => {
  // State to track the active nav item
  const [activeNav, setActiveNav] = useState('Dijkstra'); // Default active nav item

  const DIJKSTRA_DESC = [
    "Dijkstra's algorithm  is an algorithm for finding the shortest paths between nodes in a weighted graph, which may represent, for example, road networks. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later.",
    " The algorithm exists in many variants. Dijkstra's original algorithm found the shortest path between two given nodes, but a more common variant fixes a single node as the 'source' node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree.",
    " For a given source node in the graph, the algorithm finds the shortest path between that node and every other. It can also be used for finding the shortest paths from a single node to a single destination node by stopping the algorithm once the shortest path to the destination node has been determined. For example, if the nodes of the graph represent cities and costs of edge paths represent driving distances between pairs of cities connected by a direct road (for simplicity, ignore red lights, stop signs, toll roads and other obstructions), then Dijkstra's algorithm can be used to find the shortest route between one city and all other cities. A widely used application of shortest path algorithms is network routing protocols, most notably IS-IS (Intermediate System to Intermediate System) and OSPF (Open Shortest Path First). It is also employed as a subroutine in other algorithms such as Johnson's.",
    " The Dijkstra algorithm uses labels that are positive integers or real numbers, which are totally ordered. It can be generalized to use any labels that are partially ordered, provided the subsequent labels (a subsequent label is produced when traversing an edge) are monotonically non-decreasing. This generalization is called the generic Dijkstra shortest-path algorithm.",
    " Dijkstra's algorithm uses a data structure for storing and querying partial solutions sorted by distance from the start. Dijkstra's original algorithm does not use a min-priority queue and runs in time Θ(|𝑉|2)(where |𝑉|is the number of nodes). The idea of this algorithm is also given in Leyzorek et al. 1957. Fredman & Tarjan 1984 propose using a Fibonacci heap min-priority queue to optimize the running time complexity to Θ(|𝐸|+|𝑉|log⁡|𝑉|). This is asymptotically the fastest known single-source shortest-path algorithm for arbitrary directed graphs with unbounded non-negative weights. However, specialized cases (such as bounded/integer weights, directed acyclic graphs etc.) can indeed be improved further as detailed in Specialized variants. Additionally, if preprocessing is allowed algorithms such as contraction hierarchies can be up to seven orders of magnitude faster.",
    "In many fields, artificial intelligence in particular, Dijkstra's algorithm or a variant of it is known as uniform cost search and formulated as an instance of the more general idea of best-first search."
  ]
  const DFS_DESC = [
    "Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking. Extra memory, usually a stack, is needed to keep track of the nodes discovered so far along a specified branch which helps in backtracking of the graph.",
    "A version of depth-first search was investigated in the 19th century by French mathematician Charles Pierre Trémaux as a strategy for solving mazes.",
    "The time and space analysis of DFS differs according to its application area. In theoretical computer science, DFS is typically used to traverse an entire graph, and takes time 𝑂(|𝑉|+|𝐸|), where |𝑉|is the number of vertices and |𝐸| the number of edges. This is linear in the size of the graph. In these applications it also uses space 𝑂(|𝑉|)in the worst case to store the stack of vertices on the current search path as well as the set of already-visited vertices. Thus, in this setting, the time and space bounds are the same as for breadth-first search and the choice of which of these two algorithms to use depends less on their complexity and more on the different properties of the vertex orderings the two algorithms produce.",
    "For applications of DFS in relation to specific domains, such as searching for solutions in artificial intelligence or web-crawling, the graph to be traversed is often either too large to visit in its entirety or infinite (DFS may suffer from non-termination). In such cases, search is only performed to a limited depth; due to limited resources, such as memory or disk space, one typically does not use data structures to keep track of the set of all previously visited vertices. When search is performed to a limited depth, the time is still linear in terms of the number of expanded vertices and edges (although this number is not the same as the size of the entire graph because some vertices may be searched more than once and others not at all) but the space complexity of this variant of DFS is only proportional to the depth limit, and as a result, is much smaller than the space needed for searching to the same depth using breadth-first search. For such applications, DFS also lends itself much better to heuristic methods for choosing a likely-looking branch. When an appropriate depth limit is not known a priori, iterative deepening depth-first search applies DFS repeatedly with a sequence of increasing limits. In the artificial intelligence mode of analysis, with a branching factor greater than one, iterative deepening increases the running time by only a constant factor over the case in which the correct depth limit is known due to the geometric growth of the number of nodes per level.",
    "DFS may also be used to collect a sample of graph nodes. However, incomplete DFS, similarly to incomplete BFS, is biased towards nodes of high degree."
  ]
  const BFS_DESC = [
    "Breadth-first search (BFS) is an algorithm for searching a tree data structure for a node that satisfies a given property. It starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level. Extra memory, usually a queue, is needed to keep track of the child nodes that were encountered but not yet explored.",
    "For example, in a chess endgame, a chess engine may build the game tree from the current position by applying all possible moves and use breadth-first search to find a win position for White. Implicit trees (such as game trees or other problem-solving trees) may be of infinite size; breadth-first search is guaranteed to find a solution node if one exists.",
    "In contrast, (plain) depth-first search (DFS), which explores the node branch as far as possible before backtracking and expanding other nodes, may get lost in an infinite branch and never make it to the solution node. Iterative deepening depth-first search avoids the latter drawback at the price of exploring the tree's top parts over and over again. On the other hand, both depth-first algorithms typically require far less extra memory than breadth-first search.",
    "Breadth-first search can be generalized to both undirected graphs and directed graphs with a given start node (sometimes referred to as a 'search key'). In state space search in artificial intelligence, repeated searches of vertices are often allowed, while in theoretical analysis of algorithms based on breadth-first search, precautions are typically taken to prevent repetitions.",
    "BFS and its application in finding connected components of graphs were invented in 1945 by Konrad Zuse, in his (rejected) Ph.D. thesis on the Plankalkül programming language, but this was not published until 1972. It was reinvented in 1959 by Edward F. Moore, who used it to find the shortest path out of a maze, and later developed by C. Y. Lee into a wire routing algorithm (published in 1961)."
  ]
  const BELLMAN_FORD_DESC = [
    "The Bellman–Ford algorithm is an algorithm that computes shortest paths from a single source vertex to all of the other vertices in a weighted digraph. It is slower than Dijkstra's algorithm for the same problem, but more versatile, as it is capable of handling graphs in which some of the edge weights are negative numbers. The algorithm was first proposed by Alfonso Shimbel (1955), but is instead named after Richard Bellman and Lester Ford Jr., who published it in 1958 and 1956, respectively. Edward F. Moore also published a variation of the algorithm in 1959, and for this reason it is also sometimes called the Bellman–Ford–Moore algorithm.",
    "Negative edge weights are found in various applications of graphs. This is why this algorithm is useful.[4] If a graph contains a 'negative cycle' (i.e. a cycle whose edges sum to a negative value) that is reachable from the source, then there is no cheapest path: any path that has a point on the negative cycle can be made cheaper by one more walk around the negative cycle. In such a case, the Bellman–Ford algorithm can detect and report the negative cycle.",
    "Like Dijkstra's algorithm, Bellman–Ford proceeds by relaxation, in which approximations to the correct distance are replaced by better ones until they eventually reach the solution. In both algorithms, the approximate distance to each vertex is always an overestimate of the true distance, and is replaced by the minimum of its old value and the length of a newly found path.",
    "However, Dijkstra's algorithm uses a priority queue to greedily select the closest vertex that has not yet been processed, and performs this relaxation process on all of its outgoing edges; by contrast, the Bellman–Ford algorithm simply relaxes all the edges, and does this |𝑉|−1 times, where |𝑉| is the number of vertices in the graph.",
    "In each of these repetitions, the number of vertices with correctly calculated distances grows, from which it follows that eventually all vertices will have their correct distances. This method allows the Bellman–Ford algorithm to be applied to a wider class of inputs than Dijkstra's algorithm. The intermediate answers depend on the order of edges relaxed, but the final answer remains the same."
  ]
  const A_STAR_DESC = [
    "A* (pronounced 'A-star') is a graph traversal and pathfinding algorithm, which is used in many fields of computer science due to its completeness, optimality, and optimal efficiency. Given a weighted graph, a source node and a goal node, the algorithm finds the shortest path (with respect to the given weights) from source to goal.",
    "One major practical drawback is its 𝑂(𝑏𝑑) space complexity where d is the depth of the solution (the shortest path) and b is the branching factor (the average number of successors per state), as it stores all generated nodes in memory. Thus, in practical travel-routing systems, it is generally outperformed by algorithms that can pre-process the graph to attain better performance, as well as by memory-bounded approaches; however, A* is still the best solution in many cases.",
    "Peter Hart, Nils Nilsson and Bertram Raphael of Stanford Research Institute (now SRI International) first published the algorithm in 1968. It can be seen as an extension of Dijkstra's algorithm. A* achieves better performance by using heuristics to guide its search.",
    "Compared to Dijkstra's algorithm, the A* algorithm only finds the shortest path from a specified source to a specified goal, and not the shortest-path tree from a specified source to all possible goals. This is a necessary trade-off for using a specific-goal-directed heuristic. For Dijkstra's algorithm, since the entire shortest-path tree is generated, every node is a goal, and there can be no specific-goal-directed heuristic."
  ]

  const displayParagraphs = (paragraphs, img = null) => {
    return paragraphs.map((paragraph, index) => {
      return (index === 0 && img != null ?
        (
          <>
          <p key={index}>{paragraph}</p>
          <img style={{width: '100%'}} src={img} />
          </>
        ) : (<p key={index}>{paragraph}</p>));
    })
  }
  // Content for each algorithm
  const getAlgorithmContent = (algorithm) => {
    switch (algorithm) {
      case 'Dijkstra':
        return displayParagraphs(DIJKSTRA_DESC, DIJKSTRAIMG);
      case 'DFS':
        return displayParagraphs(DFS_DESC);
      case 'BFS':
        return displayParagraphs(BFS_DESC);
      case 'Bellman-Ford':
        return displayParagraphs(BELLMAN_FORD_DESC);
      case 'A*':
        return displayParagraphs(A_STAR_DESC);
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
          {getAlgorithmContent(activeNav)}
        </div>
      </main>
    </div>
  );
};

export default Algorithm;
