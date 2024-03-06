package edu.ufl.trailblazers.controller;

import edu.ufl.trailblazers.constants.DefaultMazeSize;
import edu.ufl.trailblazers.requests.PatchRequestBody;
import edu.ufl.trailblazers.requests.PostRequestBody;
import edu.ufl.trailblazers.requests.PutRequestBody;
import edu.ufl.trailblazers.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<String> getFailedDeserializationResponse(String missingProperties) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not receive " + missingProperties +
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
    * - The only way to configure maze size is via POST /maze/size/default or POST /maze/size/custom.
    * - Once a maze is initialized, there is no way to change its size unless DELETE /maze is called and a new maze is
    *   initialized.
    */

    @PostMapping() // Including a body with custom maze dimensions is for testing purposes.
    public ResponseEntity<String> initialize(@RequestBody(required = false) PostRequestBody body) {
        if (mazeService.getMaze() != null) {
            return mazeFound;
        }

        Integer height, width; // Null unless JSON deserialization is successful.
        if (body == null) {
            height = DefaultMazeSize.HEIGHT;
            width = DefaultMazeSize.WIDTH;
        }
        else {
            height = body.getHeight();
            width = body.getWidth();
        }

        if (height == null || width == null) { // Valid JSON body, but deserialization failed for height and/or width.
            if (width != null) {
                return getFailedDeserializationResponse("height");
            }
            if (height != null) {
                return getFailedDeserializationResponse("width");
            }
            return getFailedDeserializationResponse("height and width");
        }
        if (height < 0 || width < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot initialize " + height + "x" + width +
                    " maze because negative dimensions are not allowed.");
        }
        if (height * width < 2) { // Mazes must be able to fit the start and finish cell.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot initialize " + height + "x" + width +
                    " maze because mazes must have at least 2 cells.");
        }
        mazeService.initialize(height, width);
        return ResponseEntity.status(HttpStatus.CREATED).body("" + height + "x" + width +
                " maze has been initialized.");
    }

    @PutMapping()
    public ResponseEntity<String> configure(@RequestBody PutRequestBody body) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }

        Integer configId = body.getConfigId(); // Null unless JSON deserialization is successful.

        if (configId == null) { // Valid JSON body, but deserialization failed for configId.
            return getFailedDeserializationResponse("configId");
        }
        if (configId == 0) {
            mazeService.setToDefault(); // Resets the maze to one of the same size with default configuration.
            return ResponseEntity.status(HttpStatus.OK).body("Maze has been updated to default configuration with size "
                    + mazeService.getRowCount() + "x" + mazeService.getColCount() + ".");
        }
        if (configId == 1 || configId == 2 || configId == 3) {
            mazeService.setToPreset(configId); // Sets the maze to a preset configuration with the passed-in ID.
            return ResponseEntity.status(HttpStatus.OK).body("Maze has been updated to preset configuration " + configId
                    + " with size " + mazeService.getRowCount() + "x" + mazeService.getColCount() + ".");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Config ID " + configId +
                " does not exist. Acceptable config IDs are 0, 1, 2, and 3.");
    }

    @PatchMapping("/cells/{row}/{col}")
    public ResponseEntity<String> editCell(@PathVariable int row, @PathVariable int col,
                                           @RequestBody PatchRequestBody body) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (mazeService.isLocationInvalid(row, col)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location " + stringifyCoords(row, col) +
                    " is invalid for a maze of size " + mazeService.getRowCount() + "x" + mazeService.getColCount() +
                    ".");
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

    private ResponseEntity<String> destroyWall(int row, int col) {
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
        return ResponseEntity.status(HttpStatus.OK).body("Wall has been destroyed at location " +
                stringifyCoords(row, col) + ".");
    }

    private ResponseEntity<String> buildWall(int row, int col) {
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
        return ResponseEntity.status(HttpStatus.OK).body("Wall has been built at location " + stringifyCoords(row, col)
                + ".");
    }

    private ResponseEntity<String> moveStart(int row, int col) {
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already a start cell.");
        }
        if (mazeService.isFinishCell(row, col)) {
            return getOverrideFinishResponse(row, col);
        }

        int prevRow = mazeService.getStart().row();
        int prevCol = mazeService.getStart().col();
        mazeService.moveStart(row, col); // Moves the start cell to this location. Previous location becomes empty.

        return ResponseEntity.status(HttpStatus.OK).body("Start cell has been moved to location " +
                stringifyCoords(row, col) + ", and its previous location " + stringifyCoords(prevRow, prevCol) +
                " is now empty.");
    }

    private ResponseEntity<String> moveFinish(int row, int col) {
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location " + stringifyCoords(row, col) +
                    " is already a finish cell.");
        }
        if (mazeService.isStartCell(row, col)) {
            return getOverrideStartResponse(row, col);
        }

        int prevRow = mazeService.getFinish().row();
        int prevCol = mazeService.getFinish().col();
        mazeService.moveFinish(row, col); // Moves the finish cell to this location. Previous location becomes empty.

        return ResponseEntity.status(HttpStatus.OK).body("Finish cell has been moved to location " +
                stringifyCoords(row, col) + ", and its previous location " + stringifyCoords(prevRow, prevCol) +
                " is now empty.");
    }

    @GetMapping() // For testing purposes.
    public ResponseEntity<?> getMaze() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        return ResponseEntity.status(HttpStatus.OK).body(mazeService.getMaze());
    }

    @DeleteMapping() // For testing purposes.
    public ResponseEntity<String> deleteMaze() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        mazeService.deleteMaze();
        return ResponseEntity.status(HttpStatus.OK).body("Maze has been deleted.");
    }
}
