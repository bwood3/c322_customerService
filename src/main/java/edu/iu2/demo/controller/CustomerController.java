package edu.iu2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.iu2.demo.model.Customer;
import edu.iu2.demo.repository.CustomerRepository;
import jakarta.validation.Valid;


//tell framework this is a controller
@RestController
//distinguish between request with specified url
@RequestMapping("/customers")
public class CustomerController {

    //make reference of repository, note, spring boot has standard methods built in
    private CustomerRepository repository;

    //Spring is creating our object -> Spring knows to create object of this class because we annotated as @RestController
    // -> this is aspect oriented programming (we are adding code to spring through annotations)
    //Problem: every time "new" keyword added we are binding code -> a dependency is added (however we want -
    // - to code to interfaces and avoid binding -> we want inversion of control)
    //Spring has dependency injection to create the object, but we need to tell the framework we want an instance of the Customer-
    //-Repository class -> by adding argument Spring will know to add (we do this by adding @Repository in CustomerRepository.java)
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    //Get localhost:8080/customers <-will execute following code
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> findAll(){
        return repository.findAll();
    }

    //find by ID added
    //map url to ID added to GetMapping argument
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer findByID(@PathVariable int id)
    {
        List<Customer> customers = repository.findAll();
        return customers.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }


    //we must tell spring to validate customer information (see Customer class <- using validation package)
    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        Customer newCustomer = repository.save(customer);
        // Fetch the saved customer from the database
        Customer savedCustomer = repository.findById(newCustomer.getId()).orElse(null);
        return ResponseEntity.ok(savedCustomer);
    }

    //add update method -> we want a PUT localhost:8080/customers/"ID#" request to be handeled by this method
    //customer is in body but ID is in the path -> let Spring know
    //add ID to mapping -> "/{id}"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Customer customer, @PathVariable int id)
    {
        customer.setId(id);
        repository.save(customer);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    //annotated delete command for spring and tell spring where to look for variable with @PathVariable
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id)
    {
        Customer customer = new Customer();
        customer.setId(id);
        repository.delete(customer);
    }
}