package edu.ufl.trailblazers.controller;

import edu.ufl.trailblazers.responses.AlgorithmResult;
import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.service.AlgorithmService;
import edu.ufl.trailblazers.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maze")
public class AlgorithmController {
    private final MazeService mazeService;

    @Autowired
    public AlgorithmController(MazeService mazeService) {
        this.mazeService = mazeService;
    }

    @GetMapping("/shortest-path")
    public ResponseEntity<?> runAlgorithm(@RequestParam String algorithm) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized.");
        }

        int[][] board = mazeService.getBoard();
        Coords start = mazeService.getStart();
        Coords finish = mazeService.getFinish();

        AlgorithmResult result = switch (algorithm) {
            case "bfs" -> AlgorithmService.runBFS(board, start);
            case "dfs" -> AlgorithmService.runDFS(board, start);
            case "dijkstra" -> AlgorithmService.runDijkstra(board, start);
            case "bellman-ford" -> AlgorithmService.runBellmanFord(board, start, finish);
            case "a-star" -> AlgorithmService.runAStar(board, start, finish);
            default -> null;
        };

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algorithm \"" + algorithm + "\" does not exist. " +
                    "Acceptable algorithms are \"bfs\", \"dfs\", \"dijkstra\", \"bellman-ford\", and \"a-star\".");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
