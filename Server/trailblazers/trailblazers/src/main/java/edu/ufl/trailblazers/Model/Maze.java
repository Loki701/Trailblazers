package edu.ufl.trailblazers.Model;

import static edu.ufl.trailblazers.Model.CellType.*;

public class Maze {
    private final int[][] board;
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
    }

    // Initializes maze in a preset configuration.
    public Maze(int presetID) {
        switch (presetID) {
            case 1, 2, 3 -> {
                board = MazeConfiguration.getPresetBoard(presetID);
                start = MazeConfiguration.getPresetStart(presetID);
                finish = MazeConfiguration.getPresetFinish(presetID);
            }
            default -> throw new IllegalArgumentException(); // MazeController ensures passed-in presetID is 1-3.
        }
    }

    // Flips wall status at the passed-in location. Returns true if a wall was built or false if a wall was destroyed.
    public boolean editWall(int row, int col) {
        int currentStatus = board[row][col];
        if (currentStatus == EMPTY.value) {
            board[row][col] = WALL.value;
            return true;
        }
        else if (currentStatus == WALL.value) {
            board[row][col] = EMPTY.value;
            return false;
        }
        else { // MazeController ensures passed-in location isn't start or finish.
            throw new IllegalArgumentException("Server Bug: Cannot build a wall on start or finish cell");
        }
    }

    // Moves start to the passed-in location.
    public void moveStart(int row, int col) {
        board[start.row()][start.row()] = EMPTY.value;
        board[row][col] = START.value;
        start = new Coords(row, col);
    }

    // Moves finish to the passed-in location.
    public void moveFinish(int row, int col) {
        board[finish.row()][finish.row()] = EMPTY.value;
        board[row][col] = FINISH.value;
        finish = new Coords(row, col);
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

    public Coords getStart() {
        return start;
    }

    public Coords getFinish() {
        return finish;
    }
}
