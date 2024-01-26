package edu.ufl.trailblazers.Controller;

import edu.ufl.trailblazers.Model.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrailblazersAppController {

    //    private final ServiceName s ;
    //used for dependency injection
//    @Autowired
//    public ServiceController(ServiceName s){
//        this.s = s;
//    }
    //Endpoint
    @GetMapping(path = "/runAlgo")
    public String helloWorld(@RequestBody TaskInfo taskInfo){
        if(taskInfo.getMaze().size() != taskInfo.getMaze().get(0).size()){
            return "Map is not an nxn grid";
        }
        if(taskInfo.getAlgorithmName().isBlank()){
            return "invalid algorithm";
        }
        return taskInfo.getAlgorithmName();
    }
}
