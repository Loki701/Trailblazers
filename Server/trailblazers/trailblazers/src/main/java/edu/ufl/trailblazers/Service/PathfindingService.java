package edu.ufl.trailblazers.Service;

import edu.ufl.trailblazers.Model.Cell;
import edu.ufl.trailblazers.Model.TraversalResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static edu.ufl.trailblazers.Model.CellType.*;

@Service
public class PathfindingService {
    private static Cell start;
    private static Cell finish;

    // Checks if the passed-in maze is valid. Initializes start and finish member variables if so.
    private static boolean isValidMaze(int[][] maze) {
        int startCount = 0;
        int finishCount = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == START.getValue()) {
                    startCount++;
                    if (startCount > 1) { // A valid maze has only one start cell.
                        return false;
                    }
                    start = new Cell(i,j);
                }
                else if (maze[i][j] == FINISH.getValue()) {
                    finishCount++;
                    if (finishCount > 1) { // A valid maze has only one finish cell.
                        return false;
                    }
                    finish = new Cell(i,j);
                }
                else if (maze[i][j] != EMPTY.getValue() && maze[i][j] != WALL.getValue()) {
                    return false; // A valid maze contains only 0, 1, 2, or 3.
                }
            }
        }
        return true;
    }

    // Given a valid maze, find a path from start to finish using Breadth First Search.
    public static TraversalResult traverseBfs(int[][] maze) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        // Directions to check for neighbors are: {up, right, down, left}.
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        // Data structures:
        boolean[][] bfsVisited = new boolean[rowCount][colCount]; // Completely false by default.
        Queue<Cell> bfsQ = new LinkedList<>();
        Queue<Cell> visitOrder = new LinkedList<>(); // Returned in TraversalResult.

        // Start was found by isValidMaze().
        bfsVisited[start.row()][start.col()] = true;
        bfsQ.add(start);
        visitOrder.add(start);

        while (!bfsQ.isEmpty()) {
            Cell curr = bfsQ.poll();

            for (int i = 0; i < 4; i++) { // Each cell has four possible neighbors to check.
                // Get coords of current neighbor.
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                // If current neighbor is within bounds...
                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    // If current neighbor is not a wall and has not been visited...
                    if (maze[newRow][newCol] != WALL.getValue() && !bfsVisited[newRow][newCol]) {
                        Cell unvisitedNeighbor = new Cell(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        // If current neighbor is the finish...
                        if (newRow == finish.row() && newCol == finish.col()) {
                            return new TraversalResult(true, visitOrder); // Complete path found.
                        }

                        bfsVisited[newRow][newCol] = true;
                        bfsQ.add(unvisitedNeighbor);
                    }
                }
            }
        }

        // If bfsQ is empty and end isn't reached, maze can't be finished.
        return new TraversalResult(false, visitOrder);
    }

    // Given a valid maze, find a path from start to finish using Depth First Search. Same logic as BFS, but with a
    // stack instead of a queue.
    public static TraversalResult traverseDfs(int[][] maze) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        boolean[][] dfsVisited = new boolean[rowCount][colCount];
        Stack<Cell> dfsStk = new Stack<>();
        Queue<Cell> visitOrder = new LinkedList<>();

        dfsVisited[start.row()][start.col()] = true;
        dfsStk.push(start);
        visitOrder.add(start);

        while (!dfsStk.isEmpty()) {
            Cell curr = dfsStk.pop();

            for (int i = 0; i < 4; i++) {
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    if (maze[newRow][newCol] != WALL.getValue() && !dfsVisited[newRow][newCol]) {
                        Cell unvisitedNeighbor = new Cell(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);

                        if (newRow == finish.row() && newCol == finish.col()) {
                            return new TraversalResult(true, visitOrder);
                        }

                        dfsVisited[newRow][newCol] = true;
                        dfsStk.push(unvisitedNeighbor);
                    }
                }
            }
        }

        return new TraversalResult(false, visitOrder);
    }
}
