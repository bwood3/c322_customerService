package edu.iu2.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //specify customer attributes
    private int id;
    //add annotations to ensure value not null
    //comes from validation package we added (in pom file)
    @NotEmpty(message = "name must not be empty.")
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //hash code to compare customer objects
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}



//@Entity
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//    @NotEmpty(message = "name cannot be empty.")
//    private String name;
//
//    private String email;
//
//
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == this)
//            return true;
//        if (!(o instanceof Customer)) {
//            return false;
//        }
//        Customer customer = (Customer) o;
//        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, email);
//    }
//}