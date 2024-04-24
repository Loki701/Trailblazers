package edu.ufl.trailblazers.service;

import edu.ufl.trailblazers.model.Coords;
import edu.ufl.trailblazers.responses.AlgorithmResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


import static edu.ufl.trailblazers.constants.CellType.*;

@Service
public class AlgorithmService {
    private static class Node implements Comparable<Node>{
        int row, col, distance;
        public Node(int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node other){
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static Queue<Coords> NodeListToCoordsQueue(List<Node> nodeList){
        Queue<Coords> coordsQueue = new LinkedList<>();
        for(Node node : nodeList){
            coordsQueue.add(new Coords(node.row, node.col));
        }
        return coordsQueue;
    }

    // Reverses a queue in place.
    private static void reverseQueue(Queue<Coords> q) {
        Stack<Coords> stk = new Stack<>();
        while (!q.isEmpty()) {
            stk.push(q.poll());
        }
        while (!stk.isEmpty()) {
            q.add(stk.pop());
        }
    }

    // Constructs path from start cell to finish cell using 2D array of cell parents.
    private static Queue<Coords> constructPath(Coords[][] parents, Coords start, Coords finish) {
        Queue<Coords> path = new LinkedList<>();
        Coords curr = finish;

        while (!curr.equals(start)) {
            path.add(curr);
            curr = parents[curr.row()][curr.col()];
        }
        path.add(start);

        reverseQueue(path); // Reverse order of path in place so that it goes from start to finish.
        return path;
    }

    // Given a valid maze, find a path from start to finish using Breadth First Search.
    public static AlgorithmResult runBFS(int[][] maze, Coords start) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        // Directions to check for neighbors are: {up, right, down, left}.
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        // Data structures:
        boolean[][] bfsVisited = new boolean[rowCount][colCount]; // Completely false by default.
        Queue<Coords> bfsQ = new LinkedList<>();
        Queue<Coords> visitOrder = new LinkedList<>(); // Returned in TraversalResult.
        Coords[][] parents = new Coords[rowCount][colCount]; // Parents (preceding cell in traversal) of each maze cell.

        // Begin BFS.
        long startTime = System.nanoTime();
        bfsVisited[start.row()][start.col()] = true;
        bfsQ.add(start);
        visitOrder.add(start);

        while (!bfsQ.isEmpty()) {
            Coords curr = bfsQ.poll();

            for (int i = 0; i < 4; i++) { // Each cell has four possible neighbors to check.
                // Get coords of current neighbor.
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                // If current neighbor is within bounds...
                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    // If current neighbor is not a wall and has not been visited...
                    if (maze[newRow][newCol] != WALL && !bfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);
                        parents[newRow][newCol] = curr;

                        // If current neighbor is the finish...
                        if (maze[newRow][newCol] == FINISH) { // Complete path found.
                            Queue<Coords> shortestPath = constructPath(parents, start, unvisitedNeighbor);
                            long executionTime = System.nanoTime() - startTime;

                            return new AlgorithmResult(true, executionTime, shortestPath, visitOrder);
                        }

                        bfsVisited[newRow][newCol] = true;
                        bfsQ.add(unvisitedNeighbor);
                    }
                }
            }
        }

        // If bfsQ is empty and end isn't reached, maze can't be finished.
        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(false, executionTime, null, visitOrder);
    }

    // Given a valid maze, find a path from start to finish using Depth First Search. Same logic as BFS, but with a
    // stack instead of a queue.
    public static AlgorithmResult runDFS(int[][] maze, Coords start) {
        int rowCount = maze.length;
        int colCount = maze[0].length;

        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};

        boolean[][] dfsVisited = new boolean[rowCount][colCount];
        Stack<Coords> dfsStk = new Stack<>();
        Queue<Coords> visitOrder = new LinkedList<>();
        Coords[][] parents = new Coords[rowCount][colCount];

        long startTime = System.nanoTime();
        dfsVisited[start.row()][start.col()] = true;
        dfsStk.push(start);
        visitOrder.add(start);

        while (!dfsStk.isEmpty()) {
            Coords curr = dfsStk.pop();

            for (int i = 0; i < 4; i++) {
                int newRow = curr.row() + rowDirections[i];
                int newCol = curr.col() + colDirections[i];

                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
                    if (maze[newRow][newCol] != WALL && !dfsVisited[newRow][newCol]) {
                        Coords unvisitedNeighbor = new Coords(newRow, newCol);
                        visitOrder.add(unvisitedNeighbor);
                        parents[newRow][newCol] = curr;

                        if (maze[newRow][newCol] == FINISH) {
                            Queue<Coords> path = constructPath(parents, start, unvisitedNeighbor);
                            long executionTime = System.nanoTime() - startTime;

                            return new AlgorithmResult(true, executionTime, path, visitOrder);
                        }

                        dfsVisited[newRow][newCol] = true;
                        dfsStk.push(unvisitedNeighbor);
                    }
                }
            }
        }

        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(false, executionTime, null, visitOrder);
    }

    public static AlgorithmResult runDijkstra(int[][] maze, Coords start) {
        int rowCount = maze.length, colCount = maze[0].length;
        int [][] distance = new int[rowCount][colCount];

        long startTime = System.nanoTime();
        for(int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Create a priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[][] parent = new Node[rowCount][colCount];
        pq.add(new Node(start.row(), start.col(), 0));
        distance[start.row()][start.col()] = 0;

        Queue<Coords> visitedNodes = new LinkedList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            visitedNodes.add(new Coords(curr.row, curr.col));
            if(maze[curr.row][curr.col] == FINISH){
                // for(int i = 0; i < rowCount; i++){
                //     for(int j = 0; j < colCount; j++){
                //         System.out.print(distance[i][j] + " ");
                //     }
                //     System.out.println();
                // }
                long executionTime = System.nanoTime() - startTime;
                List<Node> shortestPath = new ArrayList<>();
                while (curr != null) {
                    shortestPath.add(curr);
                    curr = parent[curr.row][curr.col];
                }
                Collections.reverse(shortestPath);
                Queue<Coords> shortestPathQ = NodeListToCoordsQueue(shortestPath);
                // Console log  shortest path q
                // for(Coords c : shortestPathQ){
                //     System.out.println(c.row() + "," + c.col());
                //     System.out.println();
                // }
                return new AlgorithmResult(true, executionTime, shortestPathQ,  visitedNodes);
            }
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];
                if(newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount){
                    if(maze[newRow][newCol] != WALL && distance[newRow][newCol] > distance[curr.row][curr.col] + 1){
                        distance[newRow][newCol] = distance[curr.row][curr.col] + 1;
                        parent[newRow][newCol] = curr;
                        pq.add(new Node(newRow, newCol, distance[newRow][newCol]));
                    }
                }
            }
        }
        long executionTime = System.nanoTime() - startTime;

        return new AlgorithmResult(false, executionTime, null, visitedNodes);
    }
    //Infinity value for Bellmanford Algorithm
    private static final int INF = Integer.MAX_VALUE;
    //helper classes for points in bellmandford implementation
    static class Edge {
        Coords src, dest;
        int weight;

        Edge(Coords src, Coords dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static AlgorithmResult runBellmanFord(int[][] maze, Coords start, Coords finish) {
        int n = maze.length;
        int m = maze[0].length;
        long startTime = System.nanoTime();
        PriorityQueue<Coords> visited = new PriorityQueue<>(Comparator.comparingInt(c -> Math.abs(c.row() - start.row()) + Math.abs(c.col() - start.col())));
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1)
                    continue;
                Coords src = new Coords(i, j);
                visited.add(src); // Add all nodes to visited set
                if (i - 1 >= 0 && maze[i - 1][j] != 1)
                    edges.add(new Edge(src, new Coords(i - 1, j), 1));
                if (i + 1 < n && maze[i + 1][j] != 1)
                    edges.add(new Edge(src, new Coords(i + 1, j), 1));
                if (j - 1 >= 0 && maze[i][j - 1] != 1)
                    edges.add(new Edge(src, new Coords(i, j - 1), 1));
                if (j + 1 < m && maze[i][j + 1] != 1)
                    edges.add(new Edge(src, new Coords(i, j + 1), 1));
            }
        }

        Map<Coords, Integer> dist = new HashMap<>();
        Map<Coords, Coords> parent = new HashMap<>();
        PriorityQueue<Coords> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Edge edge : edges) {
            dist.put(edge.src, INF);
            dist.put(edge.dest, INF);
        }

        dist.put(start, 0);
        pq.offer(start);

        while (!pq.isEmpty()) {
            Coords current = pq.poll();
            if (current.equals(finish))
                break;

            for (Edge edge : edges) {
                if (edge.src.equals(current)) {
                    int newDist = dist.get(current) + edge.weight;
                    if (newDist < dist.get(edge.dest)) {
                        dist.put(edge.dest, newDist);
                        parent.put(edge.dest, current);
                        pq.offer(edge.dest);
                        visited.add(edge.dest); // Add the visited node
                    }
                }
            }
        }
        if (!parent.containsKey(finish)) {
            long executionTime = System.nanoTime() - startTime;
            return new AlgorithmResult(false, executionTime, null, visited);
        }

        PriorityQueue<Coords> shortestPath = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Coords curr = finish;
        while (curr != null) {
            shortestPath.offer(curr);
            curr = parent.get(curr);
        }

        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(true, executionTime, shortestPath, visited);
    }


    //helper class of nodes with weight and hueristic values saved
    private static class AStarNode {
        int[] position;
        AStarNode parent;
        int g; // Cost from start node to current node
        int h; // Heuristic (estimated cost from current node to end node)
        int f; // Total cost
        public AStarNode(int[] position, AStarNode parent) {
            this.position = position;
            this.parent = parent;
            this.g = 0;
            this.h = 0;
            this.f = 0;
        }
    }
    //overide of comparator to find difference in total cost between A* nodes
    static Comparator<AStarNode> comparator = new Comparator<AStarNode>() {
            @Override
            public int compare(AStarNode n1, AStarNode n2) {
                return n1.f - n2.f;
            }
        };
    public static AlgorithmResult runAStar(int[][] maze, Coords start, Coords finish) {
        AStarNode startNode = new AStarNode(new int[]{start.row(), start.col()}, null);
        AStarNode endNode = new AStarNode(new int[]{finish.row(), finish.col()}, null);
        long startTime = System.nanoTime();

        PriorityQueue<AStarNode> openList = new PriorityQueue<>(comparator);
        HashSet<String> closedSet = new HashSet<>();
        HashSet<String> visitedSet = new HashSet<>(); // Keep track of visited coordinates
        openList.add(startNode);

        //queues for ALgorithmResults
        Queue<Coords> shortestPath = new LinkedList<>();
        Queue<Coords> Visited = new LinkedList<>();

        while (!openList.isEmpty()) {
            AStarNode current = openList.poll();

            if (current.position[0] == endNode.position[0] && current.position[1] == endNode.position[1]) {
                ArrayList<int[]> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.position);
                    shortestPath.add(new Coords(current.position[0], current.position[1]));
                    current = current.parent;
                }
                Collections.reverse(shortestPath);
                long executionTime = System.nanoTime() - startTime;
                return new AlgorithmResult(true, executionTime, shortestPath, Visited);
            }

            visitedSet.add(current.position[0] + "," + current.position[1]); // Add current position to visited set
            Visited.add(new Coords(current.position[0], current.position[1]));//add to the visited queue

            int[][] neighbors = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} }; // 4-connected grid
            for (int[] neighbor : neighbors) {
                int[] newPosition = { current.position[0] + neighbor[0], current.position[1] + neighbor[1] };

                if (newPosition[0] < 0 || newPosition[0] >= maze.length || newPosition[1] < 0 || newPosition[1] >= maze[0].length)
                    continue;

                if (maze[newPosition[0]][newPosition[1]] == 1)
                    continue;

                if (closedSet.contains(newPosition[0] + "," + newPosition[1]) || visitedSet.contains(newPosition[0] + "," + newPosition[1]))
                    continue;

                AStarNode neighborNode = new AStarNode(newPosition, current);

                neighborNode.g = current.g + 1;
                neighborNode.h = Math.abs(newPosition[0] - endNode.position[0]) + Math.abs(newPosition[1] - endNode.position[1]);
                neighborNode.f = neighborNode.g + neighborNode.h;

                openList.add(neighborNode);
            }
            closedSet.add(current.position[0] + "," + current.position[1]);
        }
        long executionTime = System.nanoTime() - startTime;
        return new AlgorithmResult(false, executionTime, null, Visited);
    }
}
