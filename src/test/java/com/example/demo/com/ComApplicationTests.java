package com.example.demo.com;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.com.model.Customer;
import com.example.demo.com.repository.CustomerRepository;

@SpringBootTest
class ComApplicationTests {

	@Autowired
	public CustomerRepository customerRepository;

	@Test
	void contextLoads() {
		Customer customer=new Customer();
		customer.setId(1);
		customer.setEmail("nikhil@gmail.com");
		customer.setOrder(10);
		customer.setPrice(45);
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(1).get());
	}
	
	
	
}
