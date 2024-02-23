package edu.ufl.trailblazers.Model;

public enum DefaultMazeSize { // Dimensions of mazes on Trailblazer website.
    HEIGHT(35), WIDTH(35);

    public final int value;

    DefaultMazeSize(int value) {
        this.value = value;
    }
}
