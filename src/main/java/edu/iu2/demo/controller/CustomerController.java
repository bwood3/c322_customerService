package edu.iu2.demo.controller;

import edu.iu2.demo.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//tell framework this is a controller
@RestController
//distinguish between request with specified url
@RequestMapping("/customers")
public class CustomerController {

    //Get localhost:8080/customers <-will execute following code
    @GetMapping
    public List<Customer> findAll(){
        return null;
    }

    //http post request comes to application
    //we want to look in body -> so add requestBody
    @PostMapping
    public int create(@RequestBody Customer customer)
    {
        return 0;
    }
}
