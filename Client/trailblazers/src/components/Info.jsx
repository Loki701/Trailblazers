import React from 'react';
import Card from 'react-bootstrap/Card';
import { Button } from 'react-bootstrap';
import { useState } from 'react';

const Info = (props) => {

    const displayText = (title) => {
        if (title === "Dijkstra") {
            return "Dijkstra's Algorithm finds the shortest path between a given node (which is called the nodesource ) and all other nodes in a graph. This algorithm uses the weights of the edges to find the path that minimizes the total distance (weight) between the source node and all other nodes";
        } else if (title === "Bellman-Ford") {
            return "The Bellman-Ford algorithm is a graph search algorithm that finds the shortest path between a source vertex and all other vertices in a graph. It can be used on both weighted and unweighted graphs.";
        } else if (title === "Breadth-first search") {
            return "The breadth-first search (BFS) algorithm searches a tree or graph data structure for a node that meets a set of criteria. It starts at the root of the tree or graph and explores all nodes at the current depth level before moving on to nodes at the next depth level.";
        }
        else if (title === "Depth-first search") {
            return "Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node and explores as far as possible along each branch before backtracking.";
        }
    };

    return (
        <div className="d-flex justify-content-center">
            <Card className="info-card" style={{ width: '100vh', height: '20vh', backgroundColor: 'gainsboro', marginBottom: '40px', }}>
                <Card.Body>
                    <Card.Title className="info-card-title">{props.title}</Card.Title>
                    <Card.Text className="info-card-text">
                        <script>
                            console.log(props.title);
                        </script>
                        {displayText(props.title)}
                    </Card.Text>
                    <div style={{ display: 'flex', justifyContent: 'center' }}>
                        <button className="btn btn-outline-info">Learn more</button>
                    </div>
                </Card.Body>
            </Card>
        </div>
    );
};

export default Info;
