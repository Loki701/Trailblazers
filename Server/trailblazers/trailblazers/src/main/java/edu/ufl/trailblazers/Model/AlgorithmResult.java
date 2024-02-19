package edu.ufl.trailblazers.Model;

import java.util.Queue;

// API response after running an algorithm.
public record AlgorithmResult(boolean isCompletable, Queue<Coords> visitOrder) {}
