package edu.ufl.trailblazers.model;

import edu.ufl.trailblazers.constants.DefaultMazeSize;

public class MazeConfiguration {
    private static final int[][] preset1Board, preset2Board, preset3Board;
    private static final Coords preset1Start, preset2Start, preset3Start;
    private static final Coords preset1Finish, preset2Finish, preset3Finish;

    static { // TODO: Design the preset maze boards and set the starts and finishes.
        int presetHeight = DefaultMazeSize.HEIGHT;
        int presetWidth = DefaultMazeSize.WIDTH;

        preset1Board = new int[presetHeight][presetWidth];
        preset1Start = new Coords(0, 0);
        preset1Finish = new Coords(presetHeight - 1, presetWidth - 1);

        preset2Board = new int[presetHeight][presetWidth];
        preset2Start = new Coords(0, 0);
        preset2Finish = new Coords(presetHeight - 1, presetWidth - 1);

        preset3Board = new int[presetHeight][presetWidth];
        preset3Start = new Coords(0, 0);
        preset3Finish = new Coords(presetHeight - 1, presetWidth - 1);

        // If more presets are added, update getPresetId() in MazeController.
    }

    // Given a valid presetId, return the corresponding board layout.
    public static int[][] getPresetBoard(int presetId) {
        return switch (presetId) {
          case 1 -> preset1Board;
          case 2 -> preset2Board;
          case 3 -> preset3Board;
          default -> throw new IllegalArgumentException("Server Bug: getPresetBoard() illegal argument");
        };
    }

    // Given a valid presetId, return its start cell location.
    public static Coords getPresetStart(int presetId) {
        return switch (presetId) {
            case 1 -> preset1Start;
            case 2 -> preset2Start;
            case 3 -> preset3Start;
            default -> throw new IllegalArgumentException("Server Bug: getPresetStart() illegal argument");
        };
    }

    // Given a valid presetId, return its finish cell location.
    public static Coords getPresetFinish(int presetId) {
        return switch (presetId) {
            case 1 -> preset1Finish;
            case 2 -> preset2Finish;
            case 3 -> preset3Finish;
            default -> throw new IllegalArgumentException("Server Bug: getPresetFinish() illegal argument");
        };
    }
}
