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

    @Autowired
    public MazeController(MazeService mazeService) {
        this.mazeService = mazeService;
    }

    @PostMapping("/init/default")
    public ResponseEntity<String> initDefault() {
        if (mazeService.getMaze() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Maze has already been initialized");
        }
        mazeService.initialize(48, 48); // Default maze dimensions.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/init/custom")
    public ResponseEntity<String> initCustom(@RequestParam int rowCount, @RequestParam int colCount) {
        if (mazeService.getMaze() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Maze has already been initialized");
        }
        if (rowCount * colCount < 2) { // Mazes must have space for a start and finish cell.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A maze must have at least two cells");
        }
        mazeService.initialize(rowCount, colCount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/set/default")
    public ResponseEntity<String> setDefault() {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.getConfiguration() == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Maze was just set to default configuration");
        }
        mazeService.setDefault(); // Sets the maze to one of the same size with default configuration.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/set/preset")
    public ResponseEntity<String> setPreset(@RequestParam int presetID) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (1 > presetID || presetID > 3) { // Valid presetIDs are 1, 2, 3, etc. Use setDefault() to set default maze.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preset ID " + presetID + " does not exist");
        }
        if (mazeService.getConfiguration() == presetID) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Maze was just configured to preset " + presetID);
        }
        mazeService.setPreset(presetID); // Sets the maze to a preset configuration with the passed-in ID.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/edit/wall")
    public ResponseEntity<String> editWall(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the start cell");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the finish cell");
        }
        mazeService.editWall(row, col); // Flips wall status of the cell at this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/edit/move/start")
    public ResponseEntity<String> moveStart(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a start cell");
        }
        mazeService.moveStart(row, col); // Moves the start cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/edit/move/finish")
    public ResponseEntity<String> moveFinish(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a finish cell");
        }
        mazeService.moveFinish(row, col); // Moves the finish cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get/board")
    public ResponseEntity<int[][]> getBoard() {
        int[][] board = mazeService.getBoard();
        if (board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }
}
