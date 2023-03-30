package edu.iu2.demo.repository;

import edu.iu2.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    //get all customers
//    List<Customer> findAll();
//
//    //create new customers
//    int create(Customer customer);
//
//    void update(Customer customer, int id);
//
//    //delete customer by ID
//    void delete(int id);
}
