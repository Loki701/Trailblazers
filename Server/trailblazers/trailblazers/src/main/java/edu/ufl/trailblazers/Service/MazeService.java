package edu.ufl.trailblazers.Service;

import edu.ufl.trailblazers.Model.Coords;
import edu.ufl.trailblazers.Model.Maze;
import edu.ufl.trailblazers.Model.MazeConfiguration;
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

    public void editWall(int row, int col) {
        maze.editWall(row, col);
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

    public MazeConfiguration getConfiguration() {
        return maze.getConfiguration();
    }

    public Coords getStart() {
        return maze.getStart();
    }

    public Coords getFinish() {
        return maze.getFinish();
    }

    public boolean isStartCell(int row, int col) {
        Coords start = maze.getStart();
        return start.row() == row && start.col() == col;
    }

    public boolean isFinishCell(int row, int col) {
        Coords finish = maze.getFinish();
        return finish.row() == row && finish.col() == col;
    }
}
