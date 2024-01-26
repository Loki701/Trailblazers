package edu.ufl.trailblazers.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrailblazersAppController {

    //Endpoint
    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
