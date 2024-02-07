package edu.ufl.trailblazers.Model;

public enum CellType {
    EMPTY(0), WALL(1), START(2), FINISH(3);

    public final int value;

    CellType(int value) {
        this.value = value;
    }
}
