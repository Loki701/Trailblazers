import React, { useEffect, useState } from 'react';

const Algorithm = () => {
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        setLoaded(true);
    }, []);
    const styles = {
        page: {
            fontFamily: 'Arial, sans-serif',
            padding: '20px',
            backgroundColor: 'black',
            opacity: loaded ? 1 : 0,
            transition: 'opacity 1s ease-in-out',
            animationName: 'fadeIn',
            animationDuration: '2s'
        },
        title: {
            color: 'white',
            textAlign: 'center',
        },
        section: {
            backgroundColor: 'black',
            borderRadius: '5px',
            padding: '20px',
            marginBottom: '20px',
        },
        sectionTitle: {
            color: 'white',
        },
        sectionContent: {
            color: 'white',
        },
        algorithm: {
            backgroundColor: 'black',
            borderRadius: '5px',
            padding: '20px',
            marginBottom: '20px',
            outline: '2px solid white', // Add white outline
        },
        algorithmTitle: {
            color: 'white',
        },
        algorithmContent: {
            color: 'white',
        },
    };

    return (
        <div style={styles.page}>
            <h1 style={styles.title}>Algorithms Explanations</h1>

            <div style={styles.section}>
                <h2 style={styles.sectionTitle}>Pathfinding Algorithms</h2>
                <p style={styles.sectionContent}>
                    Pathfinding algorithms are a category of algorithms used to find the shortest or most efficient path between two points, often referred to as nodes. These algorithms are commonly used in fields such as computer science, robotics, and video games. They work by exploring different paths in a graph, which represents the possible locations and connections, and calculating the cost or distance of each path. The goal is to find the path with the lowest cost or shortest distance. Examples of pathfinding algorithms include Dijkstra's algorithm, A*, Bellman-Ford, Breadth-First Search (BFS), and Depth-First Search (DFS).                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Dijkstra’s Algorithm</h2>
                <p style={styles.algorithmContent}>
                    Dijkstra's algorithm is a pathfinding algorithm used to determine the shortest path between nodes in a weighted graph. It was conceived by computer scientist Edsger W. Dijkstra in 1956. The algorithm works by visiting vertices in the graph starting from the source vertex, the one at which we are starting, and continually marking the next vertex that is the shortest distance away as the current vertex. It keeps track of the cumulative distance from the source and uses this to find the shortest path. It's widely used in applications like GPS for finding the shortest route between two locations.
                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Bellman-Ford Algorithm</h2>
                <p style={styles.algorithmContent}>
                    The Bellman-Ford algorithm is a graph search algorithm that computes the shortest paths from a single source vertex to all other vertices in a weighted digraph. It's capable of handling graphs with negative edge weights. The algorithm works by iteratively relaxing the edges of the graph, which means it continually shortens the calculated distances between vertices until it reaches the shortest possible distances. However, if a negative cycle (a cycle whose edges sum to a negative value) is reachable from the source, the algorithm is able to detect it.                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Depth-First Search</h2>
                <p style={styles.algorithmContent}>
                    Depth-First Search (DFS) is an algorithm used for traversing or searching tree or graph data structures. The algorithm starts at the root node (or some arbitrary node in the case of a graph) and explores as far as possible along each branch before backtracking. This means it ventures deep into a graph, visiting nodes as far away from the start as possible and only backtracks when it has exhausted all possible paths. This makes DFS well-suited for tasks such as topological sorting, detecting cycles, or finding paths in a maze.                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Breadth-First Search</h2>
                <p style={styles.algorithmContent}>
                    Breadth-First Search (BFS) is a graph traversal algorithm that explores all the vertices of a graph in breadth-first order, meaning it explores all the vertices at the current level before moving on to the vertices at the next level. It starts from a source node and visits its adjacent nodes first before moving to the next level nodes. BFS uses a queue data structure to keep track of nodes to be explored. It's commonly used in applications like network routing, social networking, and AI for games to find the shortest path between two nodes.                </p>
            </div>
            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>A Star</h2>
                <p style={styles.algorithmContent}>
                    A* (A Star) is a pathfinding algorithm that is widely used in graph traversal and plotting an efficiently directed path between multiple points, called nodes. It is known for its performance and accuracy. The algorithm calculates a 'f' value for each node, which is the sum of 'g' (the cost of the path from the start node) and 'h' (the heuristic estimate of the cost of the path to the goal). At each step, it selects the node with the lowest 'f' value. This makes A* particularly effective in applications like games, robotics, and GPS navigation where finding the most efficient route is crucial.                </p>
            </div>
        </div>
    );
};

export default Algorithm;