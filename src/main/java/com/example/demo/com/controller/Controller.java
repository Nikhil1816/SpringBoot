package com.example.demo.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.com.beans.customerWrapper;
import com.example.demo.com.exception.RecordNotFoundException;
import com.example.demo.com.model.Customer;
import com.example.demo.com.service.CustomerService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
   @Autowired
   public CustomerService service;

   @PostMapping("/customers")
   public ResponseEntity<customerWrapper> saveCustomer(@RequestBody customerWrapper customerwrapper){
    customerwrapper=service.saveCustomer(customerwrapper);
    int a=customerwrapper.getOrder();
		if(a<9){
		return ResponseEntity.ok(customerwrapper);
		}else if(a==9){
			System.out.println("Sent email to Customer");
            return ResponseEntity.ok(customerwrapper);			
		}else if(a>=10&&a<20){
			customerwrapper.setPrice(customerwrapper.getPrice()-(int)(0.1*customerwrapper.getPrice()));
			return ResponseEntity.ok(customerwrapper);	
		}else{
			customerwrapper.setPrice(customerwrapper.getPrice()-(int)(0.2*customerwrapper.getPrice()));
			return ResponseEntity.ok(customerwrapper);
		}
   }
   @GetMapping("/get/customers")
	public ResponseEntity<List<Customer>> findAllProducts(){
		
		return ResponseEntity.ok(service.getAll());
	}
   @GetMapping("/customers/{id}")
	public ResponseEntity<customerWrapper> getCustomerById(@PathVariable int id) throws RecordNotFoundException{
		try{
		customerWrapper customerwrapper=service.getCustomerById(id);
		int a=customerwrapper.getOrder();
		return ResponseEntity.ok(customerwrapper);
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
    @PutMapping("/customers/{id}")
	public ResponseEntity<customerWrapper> updateCustomer(@RequestBody customerWrapper customerwrapper) throws RecordNotFoundException{
		try{
		customerwrapper=service.updateCustomerDetails(customerwrapper);
		 int a=customerwrapper.getOrder();
		 if(a<9){
		 return ResponseEntity.ok(customerwrapper);
		 }else if(a==9){
			 System.out.println("Sent email to Customer");
			 return ResponseEntity.ok(customerwrapper);			
		 }else if(a>=10&&a<20){
			 customerwrapper.setPrice(customerwrapper.getPrice()-(int)(0.1*customerwrapper.getPrice()));
			 return ResponseEntity.ok(customerwrapper);	
		 }else{
			 customerwrapper.setPrice(customerwrapper.getPrice()-(int)(0.2*customerwrapper.getPrice()));
			 return ResponseEntity.ok(customerwrapper);
		 }
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
    @DeleteMapping("/customers/{id}")
	public ResponseEntity<customerWrapper> deleteCustomerById(@PathVariable int id) throws RecordNotFoundException{
		try{
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
