package edu.ufl.trailblazers.model;

public class MazeConfiguration {
    private static final int[][] preset1Board, preset2Board, preset3Board;
    private static final Coords preset1Start, preset2Start, preset3Start;
    private static final Coords preset1Finish, preset2Finish, preset3Finish;

    static {
        preset1Board = new int[][] {
                {1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {1,2,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,0,1,1,0},
                {1,0,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,0},
                {0,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,1,0,1,0},
                {0,1,1,0,1,0,0,0,1,0,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,1},
                {0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0},
                {1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1},
                {0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
                {0,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,0,1,0,1},
                {0,1,0,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0},
                {0,1,0,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0},
                {0,1,1,1,0,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0},
                {0,0,0,0,0,1,1,0,0,0,0,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0},
                {0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0},
                {0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0},
                {1,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,0,1,0,0},
                {0,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,1,1,0,1,0,1,3,1},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1}
        };
        preset1Start = new Coords(1, 1);
        preset1Finish = new Coords(18, 28);

        preset2Board = new int[][] {
                {0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0},
                {0,1,0,0,0,1,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,0,0,1,1,1,0,0,1,0},
                {0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1,1,0,1,0},
                {0,0,0,0,0,1,0,1,0,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0},
                {1,1,1,1,0,1,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,1,0,1,1,0,1,0},
                {0,0,0,0,0,1,0,1,1,1,1,1,1,0,3,0,1,1,1,1,1,0,0,0,0,1,0,0,1,0},
                {0,1,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,1,0,1,1,1,1,1,1,0},
                {0,0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,1,1,1,1,1,0,1},
                {0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,1,0},
                {1,1,0,1,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0},
                {0,0,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
                {1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,2,0,1,0,0,0,1,0,1,0,1,0,1,1,0},
                {0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,1,1,1,1,1,0,1,0,1,0,0,0,0},
                {0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,0},
                {0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,0,0},
                {0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,0,1,1,1,1,1,1,0},
                {0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0}
        };
        preset2Start = new Coords(5, 14);
        preset2Finish = new Coords(14, 15);

        preset3Board = new int[][] {
                {2,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,0,0},
                {1,1,1,0,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,1,1,1,1},
                {0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,1,0,1,1,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,0,1,0,1,1,0,1,1,1,0},
                {0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,0,0,0,1,0},
                {0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                {1,1,0,0,1,0,0,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,0,0,1,1,1,0,1,0},
                {1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0},
                {1,1,0,0,1,0,0,0,1,1,1,0,0,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,1,0},
                {1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0},
                {1,1,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
                {3,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0}
        };
        preset3Start = new Coords(0, 0);
        preset3Finish = new Coords(19, 0);

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
