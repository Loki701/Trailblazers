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
                    Pathfinding algorithms are used to determine the shortest path between two nodes in a graph. They are widely used in applications like GPS for finding the shortest route between two locations, in games for enabling characters to reach a certain goal, and in network routing to send packets via the shortest path.
                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Dijkstra’s Algorithm</h2>
                <p style={styles.algorithmContent}>
                    Dijkstra's Algorithm is a graph search algorithm that solves the shortest-path problem for a graph with non-negative edge path costs, producing a shortest path tree.
                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Bellman-Ford Algorithm</h2>
                <p style={styles.algorithmContent}>
                    The Bellman-Ford algorithm is an algorithm that computes shortest paths from a single source vertex to all of the other vertices in a weighted digraph. It is slower than Dijkstra's algorithm for the same problem, but more versatile, as it is capable of handling graphs in which some of the edge weights are negative numbers.
                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Depth-First Search</h2>
                <p style={styles.algorithmContent}>
                    Depth-First Search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking.
                </p>
            </div>

            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>Breadth-First Search</h2>
                <p style={styles.algorithmContent}>
                    Breadth-First Search (BFS) is an algorithm for traversing or searching tree or graph data structures. It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a 'search key') and explores the neighbor nodes at the present depth prior to moving on to nodes at the next depth level.
                </p>
            </div>
            <div style={styles.algorithm}>
                <h2 style={styles.algorithmTitle}>A Star</h2>
                <p style={styles.algorithmContent}>
                A* (A Star) is a computer algorithm that is widely used in pathfinding and graph traversal, the process of plotting an efficiently directed path between multiple points, called nodes. It enjoys widespread use due to its performance and accuracy. The algorithm efficiently plots a walkable path between multiple nodes, or points, on the graph. At each step it picks the node according to a value-'f' which is a parameter equal to the sum of two other parameters – 'g' (the cost of the path from the start node) and 'h' (the heuristic estimate of the cost of the path to the goal).
                </p>
            </div>
        </div>
    );
};

export default Algorithm;