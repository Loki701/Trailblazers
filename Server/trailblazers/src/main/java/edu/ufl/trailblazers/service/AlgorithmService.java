package edu.ufl.trailblazers.service;

import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.responses.AlgorithmResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import static edu.ufl.trailblazers.constants.CellType.*;

@Service
public class AlgorithmService {
    private static class Node implements Comparable<Node>{
        int row, col, distance;
        public Node(int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node other){
            return Integer.compare(this.distance, other.distance);
        }
    }
    private static Queue<Coords> NodeListToCoordsQueue(List<Node> nodeList){
        Queue<Coords> coordsQueue = new LinkedList<>();
        for(Node node : nodeList){
            coordsQueue.add(new Coords(node.row, node.col));
        }
        return coordsQueue;
    }

    // Given a valid maze, find a path from start to finish using Breadth First Search.
    public static AlgorithmResult runBFS(int[][] maze, Coords start) { // TODO: Find shortest path with BFS.
        int rowCount = maze.length;
        int colCount = maze[0].length;

        // Directions to check for neighbors are: {up, right, down, left}.
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        // Data structures:
        boolean[][] bfsVisited = new boolean[rowCount][colCount]; // Completely false by default.
        Queue<Coords> bfsQ = new LinkedList<>();
        Queue<Coords> visitOrder = new LinkedList<>(); // Returned in TraversalResult.

        // Begin BFS.
        long startTime = System.nanoTime();
        bfsVisited[start.row()][start.col()] = true;
        bfsQ.add(start);
        visitOrder.add(start);

        while (!bfsQ.isEmpty()) {
            Coords curr = bfsQ.poll();

            for (int i = 0; i < 4; i++) { // Each cell has four possible neighbors to check.
                // Get coords of current neighbor.
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                // If current neighbor is within bounds...
                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    // If current neighbor is not a wall and has not been visited...
                    if (maze[newRow][newCol] != WALL && !bfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        // If current neighbor is the finish...
                        if (maze[newRow][newCol] == FINISH) { // Complete path found.
                            long executionTime = System.nanoTime() - startTime;
                            return new AlgorithmResult(true, executionTime, null, visitOrder);
                        }

                        bfsVisited[newRow][newCol] = true;
                        bfsQ.add(unvisitedNeighbor);
                    }
                }
            }
        }

        // If bfsQ is empty and end isn't reached, maze can't be finished.
        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(false, executionTime, null, visitOrder);
    }

    // Given a valid maze, find a path from start to finish using Depth First Search. Same logic as BFS, but with a
    // stack instead of a queue.
    public static AlgorithmResult runDFS(int[][] maze, Coords start) { // TODO: Find shortest path with DFS.
        int rowCount = maze.length;
        int colCount = maze[0].length;

        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        boolean[][] dfsVisited = new boolean[rowCount][colCount];
        Stack<Coords> dfsStk = new Stack<>();
        Queue<Coords> visitOrder = new LinkedList<>();

        long startTime = System.nanoTime();
        dfsVisited[start.row()][start.col()] = true;
        dfsStk.push(start);
        visitOrder.add(start);

        while (!dfsStk.isEmpty()) {
            Coords curr = dfsStk.pop();

            for (int i = 0; i < 4; i++) {
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    if (maze[newRow][newCol] != WALL && !dfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        if (maze[newRow][newCol] == FINISH) {
                            long executionTime = System.nanoTime() - startTime;
                            return new AlgorithmResult(true, executionTime, null, visitOrder);
                        }

                        dfsVisited[newRow][newCol] = true;
                        dfsStk.push(unvisitedNeighbor);
                    }
                }
            }
        }

        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(false, executionTime, null, visitOrder);
    }

    public static AlgorithmResult runDijkstra(int[][] maze, Coords start) {
        int rowCount = maze.length, colCount = maze[0].length;
        int [][] distance = new int[rowCount][colCount];

        long startTime = System.nanoTime();
        for(int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Create a priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[][] parent = new Node[rowCount][colCount];
        pq.add(new Node(start.row(), start.col(), 0));
        distance[start.row()][start.col()] = 0;

        Queue<Coords> visitedNodes = new LinkedList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            visitedNodes.add(new Coords(curr.row, curr.col));
            if(maze[curr.row][curr.col] == FINISH){
                // for(int i = 0; i < rowCount; i++){
                //     for(int j = 0; j < colCount; j++){
                //         System.out.print(distance[i][j] + " ");
                //     }
                //     System.out.println();
                // }
                long executionTime = System.nanoTime() - startTime;
                List<Node> shortestPath = new ArrayList<>();
                while (curr != null) {
                    shortestPath.add(curr);
                    curr = parent[curr.row][curr.col];
                }
                Collections.reverse(shortestPath);
                Queue<Coords> shortestPathQ = NodeListToCoordsQueue(shortestPath);
                // Console log  shortest path q
                // for(Coords c : shortestPathQ){
                //     System.out.println(c.row() + "," + c.col());
                //     System.out.println();
                // }
                return new AlgorithmResult(true, executionTime, shortestPathQ,  visitedNodes);
            }
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];
                if(newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount){
                    if(maze[newRow][newCol] != WALL && distance[newRow][newCol] > distance[curr.row][curr.col] + 1){
                        distance[newRow][newCol] = distance[curr.row][curr.col] + 1;
                        parent[newRow][newCol] = curr;
                        pq.add(new Node(newRow, newCol, distance[newRow][newCol]));
                    }
                }
            }
        }
        long executionTime = System.nanoTime() - startTime;

        return new AlgorithmResult(false, executionTime, null, visitedNodes);
    }

    public static AlgorithmResult runBellmanFord(int[][] maze, Coords start, Coords finish) {
        throw new UnsupportedOperationException(); // TODO: Code Bellman-Ford algorithm.
    }

    public static AlgorithmResult runAStar(int[][] maze, Coords start, Coords finish) {
        throw new UnsupportedOperationException(); // TODO: Code A* algorithm.
    }
}
