package edu.iu2.demo.repository;

import edu.iu2.demo.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//inform spring we need this component and to be ready to create instance of this component when it is needed
@Repository
//here we create an in memory database
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    //get all customers
    public List<Customer> findAll()
    {
        return customers;
    }

    //create new customers
    public int create(Customer customer)
    {
        //first generate customer ID
        int id = customers.size() + 1;
        customer.setId(id);
        customers.add(customer);
        return id;
    }

    public void update(Customer customer, int id)
    {
        Customer c = getCustomerByID(id);
        //if we have the customer, update it
        if(c != null)
        {
            c.setName(customer.getName());
            c.setEmail(customer.getEmail());
        }
        //id not found case
        else {
            throw new IllegalStateException("customer id is not valid.");
        }
    }

    //delete customer by ID
    public void delete(int id)
    {
        Customer x = getCustomerByID(id);
        if(x !=null)
            customers.remove(x);
    }

    //find customer by ID
    public Customer getCustomerByID(int id)
    {
        return customers.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }
}
