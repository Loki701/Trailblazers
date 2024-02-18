package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Service.MazeService;
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
        mazeFound = ResponseEntity.status(HttpStatus.CONFLICT).body("Maze has already been initialized");
        mazeNotFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
    }

    /* NOTES:
    * - A maze must be currently initialized with a POST request for any PUT, PATCH, GET, or DELETE request to work.
    * - The only way to configure maze size is via POST /maze/size/default or POST /maze/size/custom.
    * - Once a maze is initialized, there is no way to change its size unless DELETE /maze is called and a new maze is
    * initialized.
    */

    @PostMapping("/size/default")
    public ResponseEntity<String> initDefaultSize() {
        if (mazeService.getMaze() != null) {
            return mazeFound;
        }
        int defaultRowCount = 48;
        int defaultColCount = 48;
        mazeService.initialize(defaultRowCount, defaultColCount); // Default maze dimensions.
        return ResponseEntity.status(HttpStatus.CREATED).body("" + defaultRowCount + "x" + defaultColCount +
                " maze has been initialized");
    }

    @PostMapping("/size/custom") // For testing purposes.
    public ResponseEntity<String> initCustomSize(@RequestParam int rowCount, @RequestParam int colCount) {
        if (mazeService.getMaze() != null) {
            return mazeFound;
        }
        if (rowCount * colCount < 2) { // Mazes must have space for a start and finish cell.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A maze must have at least two cells");
        }
        mazeService.initialize(rowCount, colCount);
        return ResponseEntity.status(HttpStatus.CREATED).body("" + rowCount + "x" + colCount +
                " maze has been initialized");
    }

    @PutMapping("/config/default")
    public ResponseEntity<String> setDefaultConfig() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        mazeService.setDefault(); // Sets the maze to one of the same size with default configuration.
        return ResponseEntity.status(HttpStatus.OK).body("Maze has been updated to default configuration with size " +
                mazeService.getRowCount() + "x" + mazeService.getColCount());
    }

    @PutMapping("/config/preset")
    public ResponseEntity<String> setPresetConfig(@RequestParam int presetID) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (presetID < 1 || presetID > 3) { // Valid presetIDs are 1, 2, 3, etc. Use setDefault() to set default maze.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preset ID " + presetID + " does not exist");
        }
        mazeService.setPreset(presetID); // Sets the maze to a preset configuration with the passed-in ID.
        return ResponseEntity.status(HttpStatus.OK).body("Maze has been updated to preset configuration " + presetID +
                " with size " + mazeService.getRowCount() + "x" + mazeService.getColCount());
    }

    @PatchMapping("/wall")
    public ResponseEntity<String> editWall(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (!mazeService.isLocationValid(row, col)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location (" + row + "," + col + ") is invalid" +
                    " for a maze of size " + mazeService.getRowCount() + "x" + mazeService.getColCount());
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the start cell");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the finish cell");
        }
        boolean wallWasBuilt = mazeService.editWall(row, col); // Flips wall status of the cell at this location.

        String wallStatus;
        if (wallWasBuilt) {
            wallStatus = "built";
        }
        else {
            wallStatus = "destroyed";
        }
        return ResponseEntity.status(HttpStatus.OK).body("Wall has been " + wallStatus + " at location (" + row + "," +
                col + ")");
    }

    @PatchMapping("/move/start")
    public ResponseEntity<String> moveStart(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (!mazeService.isLocationValid(row, col)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location (" + row + "," + col + ") is invalid" +
                    " for a maze of size " + mazeService.getRowCount() + "x" + mazeService.getColCount());
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a start cell");
        }
        mazeService.moveStart(row, col); // Moves the start cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).body("Start cell has been moved to location (" + row + "," + col +
                ")");
    }

    @PatchMapping("/move/finish")
    public ResponseEntity<String> moveFinish(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        if (!mazeService.isLocationValid(row, col)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location (" + row + "," + col + ") is invalid" +
                    " for a maze of size " + mazeService.getRowCount() + "x" + mazeService.getColCount());
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a finish cell");
        }
        mazeService.moveFinish(row, col); // Moves the finish cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).body("Finish cell has been moved to location (" + row + "," + col +
                ")");
    }

    @GetMapping() // For testing purposes.
    public ResponseEntity<?> getBoard() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        int[][] board = mazeService.getBoard();
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    @DeleteMapping() // For testing purposes.
    public ResponseEntity<String> deleteMaze() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        mazeService.deleteMaze();
        return ResponseEntity.status(HttpStatus.OK).body("Maze has been deleted");
    }
}
