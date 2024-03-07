package edu.ufl.trailblazers.model;

import static edu.ufl.trailblazers.constants.CellType.*;

public class Maze {
    private final int height;
    private final int width;
    private Coords start;
    private Coords finish;
    private final int[][] board;

    // Initializes maze in the default configuration with the passed-in dimensions. All cells are empty except top left
    // (start) and bottom right (finish).
    public Maze(int rowCount, int colCount) {
        board = new int[rowCount][colCount]; // Filled with 0s by default.

        board[0][0] = START;
        start = new Coords(0, 0);

        int lastRow = rowCount - 1;
        int lastCol = colCount - 1;
        board[lastRow][lastCol] = FINISH;
        finish = new Coords(lastRow, lastCol);

        height = board.length;
        width = board[0].length;
    }

    // Initializes maze in a preset configuration.
    public Maze(int presetId) {
        switch (presetId) {
            case 1, 2, 3 -> {
                board = MazeConfiguration.getPresetBoard(presetId);
                start = MazeConfiguration.getPresetStart(presetId);
                finish = MazeConfiguration.getPresetFinish(presetId);
                height = board.length;
                width = board[0].length;
            }
            default -> throw new IllegalArgumentException("Server Bug: Parameterized Maze constructor illegal " +
                    "argument"); // MazeController ensures passed-in presetId is 1-3.
        }
    }

    public void destroyWall(int row, int col) {
        if (board[row][col] == WALL) {
            board[row][col] = EMPTY;
        }
        else { // MazeController ensures passed-in location isn't start, finish, or empty.
            throw new IllegalArgumentException("Server Bug: Wall does not exist on current cell");
        }
    }

    public void buildWall(int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = WALL;
        }
        else { // MazeController ensures passed-in location isn't start, finish, or wall.
            throw new IllegalArgumentException("Server Bug: Cannot build a wall on start cell, finish cell, or an " +
                    "existing wall cell");
        }
    }

    // Moves start to the passed-in location. Previous location becomes empty.
    public void moveStart(int row, int col) {
        if (board[row][col] == FINISH) { // MazeController ensures passed-in location isn't finish.
            throw new IllegalArgumentException("Server Bug: Cannot overwrite the finish cell with the start cell");
        }
        board[start.row()][start.col()] = EMPTY;
        board[row][col] = START;
        start = new Coords(row, col);
    }

    // Moves finish to the passed-in location. Previous location becomes empty.
    public void moveFinish(int row, int col) {
        if (board[row][col] == START) { // MazeController ensures passed-in location isn't start.
            throw new IllegalArgumentException("Server Bug: Cannot overwrite the start cell with the finish cell");
        }
        board[finish.row()][finish.col()] = EMPTY;
        board[row][col] = FINISH;
        finish = new Coords(row, col);
    }

    public int[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Coords getStart() {
        return start;
    }

    public Coords getFinish() {
        return finish;
    }
}
