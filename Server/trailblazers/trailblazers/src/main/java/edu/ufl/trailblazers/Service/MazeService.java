package edu.ufl.trailblazers.Service;

import edu.ufl.trailblazers.Model.Coords;
import edu.ufl.trailblazers.Model.Maze;
import org.springframework.stereotype.Service;

@Service
public class MazeService {
    private Maze maze;

    // Initializes a maze to the default configuration with the passed-in dimensions.
    public void initialize(int rowCount, int colCount) {
        maze = new Maze(rowCount, colCount);
    }

    // Sets the maze to one of the same size with default configuration.
    public void setDefault() {
        int rowCount = maze.getRowCount();
        int colCount = maze.getColCount();
        maze = new Maze(rowCount, colCount);
    }

    // Sets the maze to a preset configuration.
    public void setPreset(int presetID) {
        maze = new Maze(presetID);
    }

    // Flips wall status at the passed-in location. Returns true if a wall was built or false if a wall was destroyed.
    public boolean editWall(int row, int col) {
        return maze.editWall(row, col);
    }

    public void moveStart(int row, int col) {
        maze.moveStart(row, col);
    }

    public void moveFinish(int row, int col) {
        maze.moveFinish(row, col);
    }

    public Maze getMaze() {
        return maze;
    }

    public int[][] getBoard() {
        return maze.getBoard();
    }

    public int getRowCount() {
        return maze.getRowCount();
    }

    public int getColCount() {
        return maze.getColCount();
    }

    public Coords getStart() {
        return maze.getStart();
    }

    public boolean isLocationValid(int row, int col) {
        return row < maze.getRowCount() && col < maze.getColCount();
    }

    public boolean isStartCell(int row, int col) {
        Coords start = maze.getStart();
        return start.row() == row && start.col() == col;
    }

    public boolean isFinishCell(int row, int col) {
        Coords finish = maze.getFinish();
        return finish.row() == row && finish.col() == col;
    }

    public void deleteMaze() {
        maze = null;
    }
}
