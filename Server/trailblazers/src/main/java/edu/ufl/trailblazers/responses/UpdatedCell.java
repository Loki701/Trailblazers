package edu.ufl.trailblazers.responses;

import edu.ufl.trailblazers.model.Coords;

// API responds with one or more UpdatedCells after successfully destroying a wall, building a wall, moving the start,
// or moving the finish.
public record UpdatedCell(String newCellType, Coords location) {}
