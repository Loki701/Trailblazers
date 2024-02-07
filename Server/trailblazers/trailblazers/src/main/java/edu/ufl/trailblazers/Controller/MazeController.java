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

    @PostMapping("/initialize")
    public ResponseEntity<String> initialize(@RequestParam int rowCount, @RequestParam int colCount) {
        if (mazeService.getMaze() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Maze has already been initialized");
        }
        if (rowCount * colCount < 2) { // Mazes must have space for a Start and Finish cell.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A maze must have at least two cells");
        }
        mazeService.initialize(rowCount, colCount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/reset")
    public ResponseEntity<String> reset() {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.getConfiguration() == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Board is already reset");
        }
        mazeService.reset(); // Resets the maze to the default empty board.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/setPreset")
    public ResponseEntity<String> setPreset(@RequestParam int presetID) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (1 > presetID || presetID > 3) { // Valid presetIDs are 1, 2, 3, etc. Use initialize() to set default maze.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preset ID " + presetID + " does not exist");
        }
        if (mazeService.getConfiguration() == presetID) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Board is already configured to preset " + presetID);
        }
        mazeService.setPreset(presetID); // Sets the maze to the preset board with the passed-in ID.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/editWall")
    public ResponseEntity<String> editWall(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the Start cell");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the Finish cell");
        }
        mazeService.editWall(row, col); // Flips wall status of the cell at this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/moveStart")
    public ResponseEntity<String> moveStart(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a Start cell");
        }
        mazeService.moveStart(row, col); // Moves the Start cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/moveFinish")
    public ResponseEntity<String> moveFinish(@RequestParam int row, @RequestParam int col) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a Finish cell");
        }
        mazeService.moveFinish(row, col); // Moves the Finish cell from the previous location to this location.
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getBoard")
    public ResponseEntity<int[][]> getBoard() {
        int[][] board = mazeService.getBoard();
        if (board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }
}
