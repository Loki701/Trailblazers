package edu.ufl.trailblazers.Service;

import edu.ufl.trailblazers.Model.Coords;
import edu.ufl.trailblazers.Model.AlgorithmResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static edu.ufl.trailblazers.Model.CellType.*;

@Service
public class AlgorithmService {
    // Given a valid maze, find a path from start to finish using Breadth First Search.
    public static AlgorithmResult runBFS(int[][] maze, Coords start, Coords finish) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        // Directions to check for neighbors are: {up, right, down, left}.
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        // Data structures:
        boolean[][] bfsVisited = new boolean[rowCount][colCount]; // Completely false by default.
        Queue<Coords> bfsQ = new LinkedList<>();
        Queue<Coords> visitOrder = new LinkedList<>(); // Returned in TraversalResult.

        // Start was found by isValidMaze().
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
                    if (maze[newRow][newCol] != WALL.value && !bfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        // If current neighbor is the finish...
                        if (newRow == finish.row() && newCol == finish.col()) {
                            return new AlgorithmResult(true, visitOrder); // Complete path found.
                        }

                        bfsVisited[newRow][newCol] = true;
                        bfsQ.add(unvisitedNeighbor);
                    }
                }
            }
        }

        // If bfsQ is empty and end isn't reached, maze can't be finished.
        return new AlgorithmResult(false, visitOrder);
    }

    // Given a valid maze, find a path from start to finish using Depth First Search. Same logic as BFS, but with a
    // stack instead of a queue.
    public static AlgorithmResult runDFS(int[][] maze, Coords start, Coords finish) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        boolean[][] dfsVisited = new boolean[rowCount][colCount];
        Stack<Coords> dfsStk = new Stack<>();
        Queue<Coords> visitOrder = new LinkedList<>();

        dfsVisited[start.row()][start.col()] = true;
        dfsStk.push(start);
        visitOrder.add(start);

        while (!dfsStk.isEmpty()) {
            Coords curr = dfsStk.pop();

            for (int i = 0; i < 4; i++) {
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    if (maze[newRow][newCol] != WALL.value && !dfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        if (newRow == finish.row() && newCol == finish.col()) {
                            return new AlgorithmResult(true, visitOrder);
                        }

                        dfsVisited[newRow][newCol] = true;
                        dfsStk.push(unvisitedNeighbor);
                    }
                }
            }
        }

        return new AlgorithmResult(false, visitOrder);
    }

    public static AlgorithmResult runDijkstra(int[][] maze, Coords start, Coords finish) {
        throw new UnsupportedOperationException();
    }
}
