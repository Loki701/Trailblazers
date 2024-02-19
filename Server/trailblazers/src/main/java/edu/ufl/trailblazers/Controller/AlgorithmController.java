package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Model.AlgorithmResult;
import edu.ufl.trailblazers.Service.AlgorithmService;
import edu.ufl.trailblazers.Service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {
    private final MazeService mazeService;
    private final ResponseEntity<String> mazeNotFound;

    @Autowired
    public AlgorithmController(MazeService mazeService) {
        this.mazeService = mazeService;
        mazeNotFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
    }

    @GetMapping("/bfs")
    public ResponseEntity<?> runBFS() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runBFS(mazeService.getBoard(), mazeService.getStart());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/dfs")
    public ResponseEntity<?> runDFS() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runDFS(mazeService.getBoard(), mazeService.getStart());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/dijkstra")
    public ResponseEntity<?> runDijkstra() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runDijkstra(mazeService.getBoard(), mazeService.getStart());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/bellman-ford")
    public ResponseEntity<?> runBellmanFord() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runBellmanFord(mazeService.getBoard(), mazeService.getStart());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/a-star")
    public ResponseEntity<?> runAStar() {
        if (mazeService.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runAStar(mazeService.getBoard(), mazeService.getStart());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
