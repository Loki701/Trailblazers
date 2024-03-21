package edu.ufl.trailblazers.service;

import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.model.Maze;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static edu.ufl.trailblazers.constants.CellType.*;

@Service
public class MazeService {
    private Maze maze;

    // If the passed-in board is legal, initializes a maze to a custom configuration. Returns whether it was successful.
    public boolean initializeIfLegal(int[][] board) {
        Optional<BoardInfo> boardInfoOp = getBoardInfoIfLegal(board);

        if (boardInfoOp.isEmpty()) { // Board is not legal.
            return false;
        }

        BoardInfo boardInfo = boardInfoOp.get();
        maze = new Maze(board, boardInfo.start(), boardInfo.finish());
        return true;
    }

    // If the passed-in board is legal (1 start, 1 finish, all remaining cells are either walls or empty, and all rows
    // have the same length), return its info.
    private Optional<BoardInfo> getBoardInfoIfLegal(int[][] board) {
        Coords start = null;
        Coords finish = null;
        int startCount = 0;
        int finishCount = 0;
        int rowLength = board[0].length;

        // Iterate through board, returning empty Optional if board is not legal.
        for (int i = 0; i < board.length; i++) {
            if (board[i].length != rowLength) {
                return Optional.empty();
            }

            for (int j = 0; j < rowLength; j++) {
                int cellValue = board[i][j];

                if (cellValue == START) {
                    startCount++;

                    if (startCount > 1) {
                        return Optional.empty();
                    }
                    start = new Coords(i, j);
                }
                else if (cellValue == FINISH) {
                    finishCount++;

                    if (finishCount > 1) {
                        return Optional.empty();
                    }
                    finish = new Coords(i, j);
                }
                else if (cellValue != EMPTY && cellValue != WALL) {
                    return Optional.empty();
                }
            }
        }

        if (startCount == 1 && finishCount == 1) {
            return Optional.of(new BoardInfo(start, finish));
        }
        return Optional.empty();
    }

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
