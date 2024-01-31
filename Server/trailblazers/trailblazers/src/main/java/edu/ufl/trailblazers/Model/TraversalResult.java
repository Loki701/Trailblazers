package edu.ufl.trailblazers.Model;

import java.util.Queue;

public record TraversalResult(boolean isCompletable, Queue<Cell> visitOrder) {}
