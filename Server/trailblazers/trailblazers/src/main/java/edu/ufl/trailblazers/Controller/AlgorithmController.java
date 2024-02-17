package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Model.AlgorithmResult;
import edu.ufl.trailblazers.Model.Coords;
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
    private final MazeService ms;
    private final ResponseEntity<String> mazeNotFound;

    @Autowired
    public AlgorithmController(MazeService mazeService) {
        ms = mazeService;
        mazeNotFound = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No maze has been initialized");
    }

    @GetMapping("/run/bfs")
    public ResponseEntity<?> runBFS() {
        if (ms.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runBFS(ms.getBoard(), ms.getStart(), ms.getFinish());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/run/dfs")
    public ResponseEntity<?> runDFS() {
        if (ms.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runDFS(ms.getBoard(), ms.getStart(), ms.getFinish());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/run/dijkstra")
    public ResponseEntity<?> runDijkstra() {
        if (ms.getMaze() == null) {
            return mazeNotFound;
        }
        AlgorithmResult result = AlgorithmService.runDijkstra(ms.getBoard(), ms.getStart(), ms.getFinish());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
