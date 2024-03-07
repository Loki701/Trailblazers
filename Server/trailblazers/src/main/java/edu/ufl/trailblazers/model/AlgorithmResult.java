package edu.ufl.trailblazers.model;

import java.util.Queue;

// API response after running an algorithm.
public record AlgorithmResult(
        boolean isCompletable,
        long executionTimeNano,
        Queue<Coords> shortestPath,
        Queue<Coords> visitOrder
) {}
