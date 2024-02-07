package edu.ufl.trailblazers.Model;

import static edu.ufl.trailblazers.Model.CellType.*;

public class Maze {
    private int[][] board;
    private int configID; // Custom configuration is -1, default is 0, preset1 is 1, preset2 is 2, etc.

    // Sets maze to the default configuration with the passed-in dimensions. All cells are empty except top left (Start)
    // and bottom right (Finish).
    public Maze(int rowCount, int colCount) {
        board = new int[rowCount][colCount]; // Filled with 0s by default.
        board[0][0] = START.value;
        board[rowCount - 1][colCount - 1] = FINISH.value;
        configID = 0;
    }

    // Sets maze to a preset configuration.
    public Maze(int presetID) { // TODO: Initialize board to preset configurations 1-3.
        switch (presetID) {
            case 1 -> {
                configID = presetID;
                throw new UnsupportedOperationException();
            }
            case 2 -> {
                configID = presetID;
                throw new UnsupportedOperationException();
            }
            case 3 -> {
                configID = presetID;
                throw new UnsupportedOperationException();
            }
            default -> throw new IllegalArgumentException(); // MazeController ensures passed-in presetID is 1-3.
        }
    }

    public int getRowCount() {
        return board.length;
    }

    public int getColCount() {
        return board[0].length;
    }

    public int getConfiguration() {
        return configID;
    }

    public int[][] getBoard() {
        return board;
    }
}
