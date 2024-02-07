package edu.ufl.trailblazers.Model;

import static edu.ufl.trailblazers.Model.CellType.*;

public class Maze {
    private int[][] board;
    private int configID; // Custom configuration is -1, default is 0, preset1 is 1, preset2 is 2, etc.
    // configID is NOT accurate if board has been edited to match a configuration, but that's okay.
    private Coords start;
    private Coords finish;

    // Initializes maze in the default configuration with the passed-in dimensions. All cells are empty except top left
    // (start) and bottom right (finish).
    public Maze(int rowCount, int colCount) {
        board = new int[rowCount][colCount]; // Filled with 0s by default.

        board[0][0] = START.value;
        start = new Coords(0, 0);

        int lastRow = rowCount - 1;
        int lastCol = colCount - 1;
        board[lastRow][lastCol] = FINISH.value;
        finish = new Coords(lastRow, lastCol);

        configID = 0;
    }

    // Initializes maze in a preset configuration.
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

    // Flips wall status at the passed-in location.
    public void editWall(int row, int col) {
        int currentStatus = board[row][col];
        if (currentStatus == EMPTY.value) {
            board[row][col] = WALL.value;
        }
        else if (currentStatus == WALL.value) {
            board[row][col] = EMPTY.value;
        }
        else {
            throw new IllegalArgumentException(); // MazeController ensures passed-in location isn't start or finish.
        }
        configID = -1;
    }

    // Moves start to the passed-in location.
    public void moveStart(int row, int col) {
        board[start.row()][start.row()] = EMPTY.value;
        board[row][col] = START.value;
        start = new Coords(row, col);
        configID = -1;
    }

    // Moves finish to the passed-in location.
    public void moveFinish(int row, int col) {
        board[finish.row()][finish.row()] = EMPTY.value;
        board[row][col] = FINISH.value;
        finish = new Coords(row, col);
        configID = -1;
    }

    public int[][] getBoard() {
        return board;
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

    public Coords getStart() {
        return start;
    }

    public Coords getFinish() {
        return finish;
    }
}
