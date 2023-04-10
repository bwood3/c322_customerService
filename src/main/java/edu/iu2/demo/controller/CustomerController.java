package edu.iu2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.iu2.demo.model.Customer;
import edu.iu2.demo.repository.CustomerRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    // Get https:localhost:8080/customers
    @GetMapping
    public List<Customer> findAll(){
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Customer customer){
        Customer addedCustomer = repository.save(customer);
        return addedCustomer.getId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        repository.save(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Customer customer = new Customer();
        customer.setId(id);
        repository.delete(customer);
    }
}


//old code:

////tell framework this is a controller
//@RestController
////distinguish between request with specified url
//@RequestMapping("/customers")
//public class CustomerController{
//
//    //make reference of repository
//    private CustomerRepository repository;
//
//    //Spring is creating our object -> Spring knows to create object of this class because we annotated as @RestController
//    // -> this is aspect oriented programming (we are adding code to spring through annotations)
//    //Problem: every time "new" keyword added we are binding code -> a dependency is added (however we want -
//    // - to code to interfaces and avoid binding -> we want inversion of control)
//    //Spring has dependency injection to create the object, but we need to tell the framework we want an instance of the Customer-
//    //-Repository class -> by adding argument Spring will know to add (we do this by adding @Repository in CustomerRepository.java)
//    public CustomerController(CustomerRepository repository) {
//        this.repository = repository;
//    }
//
//    //Get localhost:8080/customers <-will execute following code
//    @GetMapping
//    public List<Customer> findAll(){
//        return repository.findAll();
//    }
//
////    //find by ID added
////    //map url to ID added to GetMapping argument
////    @GetMapping("/{id}")
////    public Customer findByID(@PathVariable int id){return repository.getCustomerByID(id);}
//
//
//    //http post request comes to application
//    //we want to look in body -> so add requestBody
//
//    //we must tell spring to validate customer information (see Customer class <- using validation package)
////    @ResponseStatus(HttpStatus.CREATED)
////    @PostMapping
////    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
////        Customer newCustomer = repository.save(customer);
////        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
////    }
//
//    @PostMapping
//    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
//        Customer newCustomer = repository.save(customer);
//        // Fetch the saved customer from the database
//        Customer savedCustomer = repository.findById(newCustomer.getId()).orElse(null);
//        return ResponseEntity.ok(savedCustomer);
//    }
////    @PostMapping
////    public int create(@Valid @RequestBody Customer customer)
////    {
////        Customer newCustomer= repository.save(customer);
////        return newCustomer;
////    }
//
//    //add update method -> we want a PUT localhost:8080/customers/"ID#" request to be handeled by this method
//    //customer is in body but ID is in the path -> let Spring know
//    //add ID to mapping -> "/{id}"
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{id}")
//    public void update(@RequestBody Customer customer, @PathVariable int id)
//    {
//        customer.setId(id);
//        repository.save(customer);
//    }
//
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    //annotated delete command for spring and tell spring where to look for variable with @PathVariable
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable int id)
//    {
//        Customer customer = new Customer();
//        customer.setId(id);
//        repository.delete(customer);
//    }
//}