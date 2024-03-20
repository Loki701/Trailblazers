package edu.ufl.trailblazers.responses;

import edu.ufl.trailblazers.model.Coords;

import java.util.Queue;

// API response after running an algorithm.
public record AlgorithmResult(
        boolean isCompletable,
        long executionTimeNano,
        Queue<Coords> shortestPath,
        Queue<Coords> visitOrder
) {}
