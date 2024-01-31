package edu.ufl.trailblazers.Model;

public enum CellType { // Enum for readability of the maze, represented by 2D array of ints.
    EMPTY(0), WALL(1), START(2), FINISH(3);

    private final int value;

    CellType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
