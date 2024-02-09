package edu.ufl.trailblazers.Model;

public enum MazeConfiguration {
    CUSTOM(-1), DEFAULT(0), PRESET1(1), PRESET2(2), PRESET3(3);

    public final int value;

    MazeConfiguration(int value) {
        this.value = value;
    }

    private static final int[][] preset1Board, preset2Board, preset3Board;
    private static final Coords preset1Start, preset2Start, preset3Start;
    private static final Coords preset1Finish, preset2Finish, preset3Finish;

    static { // TODO: Design the preset maze boards and set the starts and finishes.
        int presetHeight = 48;
        int presetWidth = 48;
        preset1Board = new int[presetHeight][presetWidth];
        preset1Start = new Coords(0, 0);
        preset1Finish = new Coords(47, 47);

        preset2Board = new int[presetHeight][presetWidth];
        preset2Start = new Coords(0, 0);
        preset2Finish = new Coords(47, 47);

        preset3Board = new int[presetHeight][presetWidth];
        preset3Start = new Coords(0, 0);
        preset3Finish = new Coords(47, 47);
    }

    // Given a valid presetID, return the corresponding MazeConfiguration enum.
    public static MazeConfiguration getPresetEnum(int presetID) {
        return switch (presetID) {
            case 1 -> PRESET1;
            case 2 -> PRESET2;
            case 3 -> PRESET3;
            default -> throw new IllegalArgumentException("Server Bug: getPresetName() illegal argument");
        };
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
