package edu.ufl.trailblazers.requests;

// Object for Spring to deserialize MazeController PATCH request body into.
public class PatchRequestBody { // Setters aren't needed because Spring Boot uses Jackson to set fields via reflection.
    private String newCellType;

    public PatchRequestBody() {}

    public String getNewCellType() {
        return newCellType;
    }
}
