package edu.ufl.trailblazers.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
//import javax.validation.contraints.NotBlank;

public class TaskInfo {

//    @NotBlank
    private final String algorithmName;
    private ArrayList<ArrayList<Integer>> maze = new ArrayList<>();

    public TaskInfo(@JsonProperty("algoName") String algoName, @JsonProperty("maze") ArrayList<ArrayList<Integer>> maze){
        this.algorithmName = algoName;
        this.maze = maze;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public ArrayList<ArrayList<Integer>> getMaze() {
        return maze;
    }
}
