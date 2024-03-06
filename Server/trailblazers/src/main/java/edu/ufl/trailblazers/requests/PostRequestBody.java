package edu.ufl.trailblazers.requests;

// Object for Spring to deserialize MazeController POST request body into.
public class PostRequestBody { // Setters aren't needed because Spring Boot uses Jackson to set fields via reflection.
    private Integer height;
    private Integer width;

    public PostRequestBody() {}

    // Getters return null if deserialization failed.
    public Integer getHeight() {
        return height;
    }
    public Integer getWidth() {
        return width;
    }
}
