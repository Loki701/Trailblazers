package edu.ufl.trailblazers.controller;

import edu.ufl.trailblazers.constants.DefaultMazeSize;
import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.requests.PatchRequestBody;
import edu.ufl.trailblazers.requests.PostRequestBody;
import edu.ufl.trailblazers.requests.PutRequestBody;
import edu.ufl.trailblazers.responses.UpdatedCell;
import edu.ufl.trailblazers.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/maze")
public class MazeController {
    private final MazeService mazeService;
    private final ResponseEntity<String> mazeFound;
    private final ResponseEntity<String> mazeNotFound;

    @Autowired
    public MazeController(MazeService mazeService) {
        this.mazeService = mazeService;
        mazeFound = ResponseEntity.status(HttpStatus.CONFLICT).body("Maze has already been initialized.");
        mazeNotFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized.");
    }

    // Returns coordinate pair for use in response strings.
    private String stringifyCoords(int row, int col) {
        return "(" + row + "," + col + ")";
    }

    // Returns a response for when the request body is valid JSON, but deserialization failed for expected properties.
    private ResponseEntity<String> getFailedDeserializationResponse(String didntReceive) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not receive " + didntReceive +
                " in request body. Verify that the request body matches the expected input exactly.");
    }

    // Returns a response for when a PATCH request tries to overwrite the start cell.
    private ResponseEntity<String> getOverrideStartResponse(int row, int col) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                " cannot be overwritten because it contains the start cell.");
    }

    // Returns a response for when a PATCH request tries to overwrite the finish cell.
    private ResponseEntity<String> getOverrideFinishResponse(int row, int col) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                " cannot be overwritten because it contains the finish cell.");
    }

    /* NOTES:
    * - Only one maze can be initialized at a time. Repeated POST requests will fail unless a DELETE request is made.
    * - A maze must be currently initialized with a POST request for any PUT, PATCH, GET, or DELETE request to work.
    * - The only way to configure maze size is via POST /maze with a request body included.
    * - Once a maze is initialized, there is no way to change its size unless DELETE /maze is called and a new maze is
    *   initialized.
    */

    @PostMapping() // Including a body with custom maze dimensions is for testing purposes.
    public ResponseEntity<?> initialize(@RequestBody(required = false) PostRequestBody body) {
        if (mazeService.getMaze() != null) {
            return mazeFound;
        }

        // Null unless JSON deserialization is successful.
        int[][] board;
        Integer height, width;

        if (body == null) {
            height = DefaultMazeSize.HEIGHT;
            width = DefaultMazeSize.WIDTH;
            return initializeDefaultConfig(height, width);
        }

        board = body.getBoard();
        height = body.getHeight();
        width = body.getWidth();

        if (board != null) { // Client wants to initialize a maze with custom configuration.
            return initializeCustomConfig(board);
        }
        if (height != null || width != null) { // Client wants to initialize a maze with default configuration.
            if (height == null) {
                return getFailedDeserializationResponse("height");
            }
            if (width == null) {
                return getFailedDeserializationResponse("width");
            }
            return initializeDefaultConfig(height, width);
        }

        // All are null. It's unknown if client wants to initialize a maze with custom or default configuration.
        return getFailedDeserializationResponse("board in request body, or did not receive height and width");
    }

    // Initialize a maze in the default configuration with the passed-in dimensions.
    private ResponseEntity<?> initializeDefaultConfig(int height, int width) {
        if (height < 0 || width < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot initialize " + height + "x" + width +
                    " maze because negative dimensions are not allowed.");
        }
        if (height * width < 2) { // Mazes must be able to fit the start and finish cell.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot initialize " + height + "x" + width +
                    " maze because mazes must have at least 2 cells.");
        }
        mazeService.initialize(height, width);
        return ResponseEntity.status(HttpStatus.CREATED).body(mazeService.getMaze());
    }

    // Initialize a maze in a custom configuration with the passed-in board (if the board is legal).
    private ResponseEntity<?> initializeCustomConfig(int[][] board) {
        boolean mazeWasInitialized = mazeService.initializeIfLegal(board);

        if (!mazeWasInitialized) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal maze configuration. Mazes must have " +
                    "exactly one 2 and exactly one 3, all remaining cells must be either 0 or 1, and all rows must " +
                    "have the same length.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mazeService.getMaze());
    }

    @PutMapping()
    public ResponseEntity<?> configure(@RequestBody PutRequestBody body) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }

        String configuration = body.getConfiguration(); // Null unless JSON deserialization is successful.

        if (configuration == null) { // Valid JSON body, but deserialization failed for configId.
            return getFailedDeserializationResponse("configuration");
        }
        if (configuration.equals("default")) {
            mazeService.setToDefault(); // Resets the maze to one of the same size with default configuration.
            return ResponseEntity.status(HttpStatus.OK).body(mazeService.getMaze());
        }

        Optional<Integer> presetId = getPresetId(configuration);
        if (presetId.isPresent()) {
            mazeService.setToPreset(presetId.get()); // Sets the maze to a preset configuration with the passed-in ID.
            return ResponseEntity.status(HttpStatus.OK).body(mazeService.getMaze());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Configuration \"" + configuration + "\" does not " +
                "exist. Acceptable configurations are \"default\", \"preset1\", \"preset2\", and \"preset3\".");
    }

    // Given a configuration deserialized from PutRequestBody, if it maps to the ID of a preset configuration, return
    // that ID.
    private Optional<Integer> getPresetId(String configurationString) {
        return switch (configurationString) {
            case "preset1" -> Optional.of(1);
            case "preset2" -> Optional.of(2);
            case "preset3" -> Optional.of(3);
            default -> Optional.empty();
        };
    }

    @PatchMapping("/cells/{row}/{col}")
    public ResponseEntity<?> editCell(@PathVariable int row, @PathVariable int col,
                                           @RequestBody PatchRequestBody body) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (mazeService.isLocationInvalid(row, col)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location " + stringifyCoords(row, col) +
                    " is invalid for a maze of size " + mazeService.getWidth() + "x" + mazeService.getHeight() + ".");
        }

        String newCellType = body.getNewCellType(); // Null unless JSON deserialization is successful.

        if (newCellType == null) { // Valid JSON body, but deserialization failed for newCellType.
            return getFailedDeserializationResponse("newCellType");
        }
        if (newCellType.equals("empty")) {
            return destroyWall(row, col);
        }
        if (newCellType.equals("wall")) {
            return buildWall(row, col);
        }
        if (newCellType.equals("start")) {
            return moveStart(row, col);
        }
        if (newCellType.equals("finish")) {
            return moveFinish(row, col);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cell type \"" + newCellType +
                "\" is not valid. Acceptable cell types are \"empty\", \"wall\", \"start\", and \"finish\".");
    }

    private ResponseEntity<?> destroyWall(int row, int col) {
        if (mazeService.isEmptyCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already empty.");
        }
        if (mazeService.isStartCell(row, col)) {
            return getOverrideStartResponse(row, col);
        }
        if (mazeService.isFinishCell(row, col)) {
            return getOverrideFinishResponse(row, col);
        }

        mazeService.destroyWall(row, col);
        return ResponseEntity.status(HttpStatus.OK).body(new UpdatedCell("empty", new Coords(row, col)));
    }

    private ResponseEntity<?> buildWall(int row, int col) {
        if (mazeService.isWallCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already a wall.");
        }
        if (mazeService.isStartCell(row, col)) {
            return getOverrideStartResponse(row, col);
        }
        if (mazeService.isFinishCell(row, col)) {
            return getOverrideFinishResponse(row, col);
        }

        mazeService.buildWall(row, col);
        return ResponseEntity.status(HttpStatus.OK).body(new UpdatedCell("wall", new Coords(row, col)));
    }

    private ResponseEntity<?> moveStart(int row, int col) {
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already the start cell.");
        }
        if (mazeService.isFinishCell(row, col)) {
            return getOverrideFinishResponse(row, col);
        }

        Coords prevStart = mazeService.getStart();
        mazeService.moveStart(row, col); // Moves the start cell to this location. Previous location becomes empty.

        UpdatedCell[] updatedCells = {
                new UpdatedCell("empty", prevStart),
                new UpdatedCell("start", mazeService.getStart())
        };

        return ResponseEntity.status(HttpStatus.OK).body(updatedCells);
    }

    private ResponseEntity<?> moveFinish(int row, int col) {
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already the finish cell.");
        }
        if (mazeService.isStartCell(row, col)) {
            return getOverrideStartResponse(row, col);
        }

        Coords prevFinish = mazeService.getFinish();
        mazeService.moveFinish(row, col); // Moves the finish cell to this location. Previous location becomes empty.

        UpdatedCell[] updatedCells = {
                new UpdatedCell("empty", prevFinish),
                new UpdatedCell("finish", mazeService.getFinish())
        };

        return ResponseEntity.status(HttpStatus.OK).body(updatedCells);
    }

    @GetMapping("/status") 
    public Boolean getMazeStatus(){
        if(mazeService.getMaze() == null){
            return false;
        }
        return true;
    }

    @GetMapping()
    public ResponseEntity<?> getMaze() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        return ResponseEntity.status(HttpStatus.OK).body(mazeService.getMaze());
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMaze() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        mazeService.deleteMaze();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
