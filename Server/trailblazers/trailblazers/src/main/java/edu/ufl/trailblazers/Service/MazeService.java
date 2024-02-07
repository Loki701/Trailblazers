package edu.ufl.trailblazers.Service;

import edu.ufl.trailblazers.Model.Maze;
import org.springframework.stereotype.Service;

@Service
public class MazeService {
    private Maze maze;

    // Initializes a maze to the default configuration with the passed-in dimensions.
    public void initialize(int rowCount, int colCount) {
        maze = new Maze(rowCount, colCount);
    }

    // Resets the initialized maze to a fresh maze of the same size.
    public void reset() {
        int rowCount = maze.getRowCount();
        int colCount = maze.getColCount();
        maze = new Maze(rowCount, colCount);
    }

    // Initializes a maze to a preset configuration.
    public void setPreset(int presetID) {
        maze = new Maze(presetID);
    }

    public void editWall(int row, int col) {
        throw new UnsupportedOperationException(); // TODO
    }

    public void moveStart(int row, int col) {
        throw new UnsupportedOperationException(); // TODO
    }

    public void moveFinish(int row, int col) {
        throw new UnsupportedOperationException(); // TODO
    }

    public Maze getMaze() {
        return maze;
    }

    public int[][] getBoard() {
        return maze.getBoard();
    }

    public int getConfiguration() {
        return maze.getConfiguration();
    }

    public boolean isStartCell(int row, int col) {
        throw new UnsupportedOperationException(); // TODO
    }

    public boolean isFinishCell(int row, int col) {
        throw new UnsupportedOperationException(); // TODO
    }
}
