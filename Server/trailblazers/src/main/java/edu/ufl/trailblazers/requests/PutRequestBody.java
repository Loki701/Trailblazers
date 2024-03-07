package edu.ufl.trailblazers.requests;

// Object for Spring to deserialize MazeController PUT request body into.
public class PutRequestBody { // Setters aren't needed because Spring Boot uses Jackson to set fields via reflection.
    private String configuration;

    public PutRequestBody() {}

    // Getter returns null if deserialization failed.
    public String getConfiguration() {
        return configuration;
    }
}
