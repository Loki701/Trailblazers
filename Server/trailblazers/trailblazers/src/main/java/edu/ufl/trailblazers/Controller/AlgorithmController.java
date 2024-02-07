package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Model.AlgorithmResult;
import edu.ufl.trailblazers.Model.Coords;
import edu.ufl.trailblazers.Service.AlgorithmService;
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
        int[][] board = mazeService.getBoard();
        Coords start = mazeService.getStart();
        Coords finish = mazeService.getFinish();
        AlgorithmResult result = AlgorithmService.runBFS(board, start, finish);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/runDFS")
    public ResponseEntity<AlgorithmResult> runDFS() {
        int[][] board = mazeService.getBoard();
        Coords start = mazeService.getStart();
        Coords finish = mazeService.getFinish();
        AlgorithmResult result = AlgorithmService.runDFS(board, start, finish);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/runDijkstra")
    public ResponseEntity<AlgorithmResult> runDijkstra() {
        int[][] board = mazeService.getBoard();
        Coords start = mazeService.getStart();
        Coords finish = mazeService.getFinish();
        AlgorithmResult result = AlgorithmService.runDijkstra(board, start, finish);
        return ResponseEntity.ok(result);
    }
}
