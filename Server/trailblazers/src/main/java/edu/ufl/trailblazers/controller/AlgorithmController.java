package edu.ufl.trailblazers.controller;

import edu.ufl.trailblazers.responses.AlgorithmResult;
import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.service.AlgorithmService;
import edu.ufl.trailblazers.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maze/algorithms")
public class AlgorithmController {
    private final MazeService mazeService;

    @Autowired
    public AlgorithmController(MazeService mazeService) {
        this.mazeService = mazeService;
    }

    @GetMapping("/{algorithm-name}")
    public ResponseEntity<?> runAlgorithm(@PathVariable("algorithm-name") String algorithmName) {
        if (mazeService.getMaze() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized.");
        }

        int[][] board = mazeService.getBoard();
        Coords start = mazeService.getStart();

        AlgorithmResult result = switch (algorithmName) {
            case "bfs" -> AlgorithmService.runBFS(board, start);
            case "dfs" -> AlgorithmService.runDFS(board, start);
            case "dijkstra" -> AlgorithmService.runDijkstra(board, start);
            case "bellman-ford" -> AlgorithmService.runBellmanFord(board, start);
            case "a-star" -> AlgorithmService.runAStar(board, start);
            default -> null;
        };

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algorithm \"" + algorithmName +
                    "\" does not exist. Acceptable algorithms are \"bfs\", \"dfs\", \"dijkstra\", \"bellman-ford\", " +
                    "and \"a-star\".");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
