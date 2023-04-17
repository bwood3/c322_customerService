package edu.iu2.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//annotate as rest controller
//we also want requests to this controller
@RestController
@RequestMapping
public class GreetingController {

    //Get https:localhost:8080 <- we retrieve this function upon request
    //we add mapping to handle any http get request coming to application
    //we can add arguments to GetMapping("/requestN") for additional requests
    @GetMapping
    //add method to return greetings
    public String greeting()
    {
        return "Greetings!";
    }
}
