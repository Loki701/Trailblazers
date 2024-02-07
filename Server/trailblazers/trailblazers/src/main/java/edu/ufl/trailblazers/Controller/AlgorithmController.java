package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Model.AlgorithmResult;
import edu.ufl.trailblazers.Model.Maze;
import edu.ufl.trailblazers.Service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {
    private final MazeService mazeService;

    @Autowired
    public AlgorithmController(MazeService mazeService) {
        this.mazeService = mazeService;
    }

    @GetMapping("/runBFS")
    public ResponseEntity<AlgorithmResult> runBFS() {
        Maze maze = mazeService.getMaze();
        AlgorithmResult result = AlgorithmService.runBFS(maze);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/runDFS")
    public ResponseEntity<AlgorithmResult> runDFS() {
        Maze maze = mazeService.getMaze();
        AlgorithmResult result = AlgorithmService.runDFS(maze);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/runDijkstra")
    public ResponseEntity<AlgorithmResult> runDijkstra() {
        Maze maze = mazeService.getMaze();
        AlgorithmResult result = AlgorithmService.runDijkstra(maze);
        return ResponseEntity.ok(result);
    }
}
