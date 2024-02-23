package edu.ufl.trailblazers.Model;

public class MazeConfiguration {
    private static final int[][] preset1Board, preset2Board, preset3Board;
    private static final Coords preset1Start, preset2Start, preset3Start;
    private static final Coords preset1Finish, preset2Finish, preset3Finish;

    static { // TODO: Design the preset maze boards and set the starts and finishes.
        int presetHeight = DefaultMazeSize.HEIGHT.value;
        int presetWidth = DefaultMazeSize.WIDTH.value;
        preset1Board = new int[presetHeight][presetWidth];
        preset1Start = new Coords(0, 0);
        preset1Finish = new Coords(presetHeight - 1, presetWidth - 1);

        preset2Board = new int[presetHeight][presetWidth];
        preset2Start = new Coords(0, 0);
        preset2Finish = new Coords(presetHeight - 1, presetWidth - 1);

        preset3Board = new int[presetHeight][presetWidth];
        preset3Start = new Coords(0, 0);
        preset3Finish = new Coords(presetHeight - 1, presetWidth - 1);
    }

    // Given a valid presetID, return the corresponding board layout.
    public static int[][] getPresetBoard(int presetID) {
        return switch (presetID) {
          case 1 -> preset1Board;
          case 2 -> preset2Board;
          case 3 -> preset3Board;
          default -> throw new IllegalArgumentException("Server Bug: getPresetBoard() illegal argument");
        };
    }

    // Given a valid presetID, return its start cell location.
    public static Coords getPresetStart(int presetID) {
        return switch (presetID) {
            case 1 -> preset1Start;
            case 2 -> preset2Start;
            case 3 -> preset3Start;
            default -> throw new IllegalArgumentException("Server Bug: getPresetStart() illegal argument");
        };
    }

    // Given a valid presetID, return its finish cell location.
    public static Coords getPresetFinish(int presetID) {
        return switch (presetID) {
            case 1 -> preset1Finish;
            case 2 -> preset2Finish;
            case 3 -> preset3Finish;
            default -> throw new IllegalArgumentException("Server Bug: getPresetFinish() illegal argument");
        };
    }
}
