package com.example.demo.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.com.beans.customerWrapper;
import com.example.demo.com.exception.RecordNotFoundException;
import com.example.demo.com.model.Customer;
import com.example.demo.com.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public customerWrapper saveCustomer(customerWrapper inputCustomer){
        Customer customer=new Customer();
        customer.setId(inputCustomer.getId());
        customer.setRegular(inputCustomer.getRegular());
		customer.setOrder(inputCustomer.getOrder());
		customer.setEmail(inputCustomer.getEmail());
		customer.setPrice(inputCustomer.getPrice());
		customer= repository.save(customer);
		inputCustomer.setId(customer.getId());
		inputCustomer.setEmail(customer.getEmail());
		inputCustomer.setRegular(customer.getRegular());
        inputCustomer.setOrder(customer.getOrder());
		inputCustomer.setPrice(customer.getPrice());
		
		return inputCustomer;

    }
    public customerWrapper getCustomerById(int id) throws RecordNotFoundException{
		customerWrapper customerwrapper = null;
		Optional<Customer> customerData=repository.findById(id);
		if(customerData.isPresent()) {
			customerwrapper =new customerWrapper();
			Customer customer=customerData.get();
			customerwrapper.setId(customer.getId());
			customerwrapper.setEmail(customer.getEmail());
			customerwrapper.setOrder(customer.getOrder());
            customerwrapper.setRegular(customer.getRegular());
			customer.setPrice(customer.getPrice());
            return customerwrapper;
			
		}else {
			throw new RecordNotFoundException("Customer record not found");
		}
		
	}
    public customerWrapper updateCustomerDetails(customerWrapper inputCustomer) throws RecordNotFoundException{
		Optional<Customer> customerData=repository.findById(inputCustomer.getId());
		if(customerData.isPresent()) {
			customerWrapper customerwrapper=new customerWrapper();
			Customer customer=customerData.get();
			customer.setEmail(inputCustomer.getEmail());
			customer.setOrder(inputCustomer.getOrder());
            customer.setRegular(inputCustomer.getRegular());	
			customer.setPrice(inputCustomer.getPrice());
			repository.save(customer);
			customerwrapper.setId(customer.getId());
			customerwrapper.setEmail(customer.getEmail());
			customerwrapper.setRegular(customer.getRegular());
            customerwrapper.setOrder(customer.getOrder());
			customerwrapper.setPrice(customer.getPrice());
			return customerwrapper;
		}else {
			throw new RecordNotFoundException("Customer record not found");
		}
	
	}
    public String deleteById(int id) throws RecordNotFoundException{
		Optional<Customer> customerData=repository.findById(id);
		if(customerData.isPresent()) {
			Customer customer=customerData.get();
			repository.delete(customer);
			return "Deleted";
		}else {
			throw new RecordNotFoundException("Customer record not found");
		}
	}
    public List<Customer> getAll(){
		return  repository.findAll();
     	
	}

}
