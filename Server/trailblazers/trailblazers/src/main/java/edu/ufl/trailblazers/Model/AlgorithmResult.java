package edu.ufl.trailblazers.Model;

import java.util.Queue;

public record AlgorithmResult(boolean isCompletable, Queue<Coords> visitOrder) {}
