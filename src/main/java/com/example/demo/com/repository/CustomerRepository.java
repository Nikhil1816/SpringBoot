
package com.example.demo.com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.com.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer,Integer>{
    
}
