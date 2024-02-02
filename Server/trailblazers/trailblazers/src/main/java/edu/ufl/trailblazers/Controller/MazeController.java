package edu.ufl.trailblazers.Controller;

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

    @PutMapping("/editWall")
    public ResponseEntity<String> editWall(@RequestParam int row, @RequestParam int col) {
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the Start cell");
        }
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot build a wall on the Finish cell");
        }
        mazeService.editWall(row, col); // Flips wall status of the cell at this location.
        return ResponseEntity.ok().build();
    }

    @PutMapping("/moveStart")
    public ResponseEntity<String> moveStart(@RequestParam int row, @RequestParam int col) {
        if (mazeService.isStartCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a Start cell");
        }
        mazeService.moveStart(row, col); // Moves the Start cell from the previous location to this location.
        return ResponseEntity.ok().build();
    }

    @PutMapping("/moveFinish")
    public ResponseEntity<String> moveFinish(@RequestParam int row, @RequestParam int col) {
        if (mazeService.isFinishCell(row, col)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location is already a Finish cell");
        }
        mazeService.moveFinish(row, col); // Moves the Finish cell from the previous location to this location.
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset")
    public ResponseEntity<String> reset() {
        if (mazeService.isBoardReset()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Board is already reset");
        }
        mazeService.reset(); // Resets the maze to the default empty board.
        return ResponseEntity.ok().build();
    }

    @PutMapping("/setPreset")
    public ResponseEntity<String> setPreset(@RequestParam int presetID) {
        if (mazeService.isPreset() && mazeService.getPresetID() == presetID) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Board is already configured to preset " + presetID);
        }
        mazeService.setPreset(presetID); // Sets the maze to the preset board with the passed-in ID.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getMaze")
    public ResponseEntity<Maze> getMaze() {
        Maze maze = mazeService.getMaze(); // Returns back end Maze data.
        return ResponseEntity.ok(maze);
    }
}
