package edu.ufl.trailblazers.service;

import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.model.Maze;
import org.springframework.stereotype.Service;

import static edu.ufl.trailblazers.constants.CellType.*;

@Service
public class MazeService {
    private Maze maze;

    // Initializes a maze to the default configuration with the passed-in dimensions.
    public void initialize(int rowCount, int colCount) {
        maze = new Maze(rowCount, colCount);
    }

    // Sets the maze to one of the same size with default configuration.
    public void setToDefault() {
        int rowCount = maze.getHeight();
        int colCount = maze.getWidth();
        maze = new Maze(rowCount, colCount);
    }

    // Sets the maze to a preset configuration.
    public void setToPreset(int presetId) {
        maze = new Maze(presetId);
    }

    public void destroyWall(int row, int col) {
        maze.destroyWall(row, col);
    }

    public void buildWall(int row, int col) {
        maze.buildWall(row, col);
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

    public int getHeight() {
        return maze.getHeight();
    }

    public int getWidth() {
        return maze.getWidth();
    }

    public Coords getStart() {
        return maze.getStart();
    }

    public Coords getFinish() {
        return maze.getFinish();
    }

    // Returns true if the passed-in location is out of bounds.
    public boolean isLocationInvalid(int row, int col) {
        return row < 0 || row >= maze.getHeight() || col < 0 || col >= maze.getWidth();
    }

    public boolean isEmptyCell(int row, int col) {
        return maze.getBoard()[row][col] == EMPTY;
    }

    public boolean isWallCell(int row, int col) {
        return maze.getBoard()[row][col] == WALL;
    }

    public boolean isStartCell(int row, int col) {
        return maze.getBoard()[row][col] == START;
    }

    public boolean isFinishCell(int row, int col) {
        return maze.getBoard()[row][col] == FINISH;
    }

    public void deleteMaze() {
        maze = null;
    }
}
