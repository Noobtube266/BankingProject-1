package com.banking.banking.service;

import com.banking.banking.ResponseHandler.ResponseHandler;
import com.banking.banking.exception.ResourceNotFoundException;
import com.banking.banking.model.Customer;
import com.banking.banking.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> createCustomer (Customer customer){
        Customer result = customerRepository.save(customer);
        if (result == null){
            throw new ResourceNotFoundException("error fetching customer");
        }
        return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
    }

    public ResponseEntity<?> getAllCustomers(){
            List<Customer> customers = (List<Customer>) customerRepository.findAll();
            if(customers.isEmpty()){
                throw new ResourceNotFoundException("error fetching customers");
            }
            return ResponseHandler.generateResponse("Successfully retrieved customers' data!", HttpStatus.OK, customers);
    }

    public ResponseEntity<?> getCustomerById(Long depositId) throws ResourceNotFoundException{
            Customer customer = customerRepository.findById(depositId).orElse(null);
            if (customer == null){
                throw new ResourceNotFoundException("error fetching account");
            }
            return ResponseHandler.generateResponse("Successfully retrieved customer data!", HttpStatus.OK, customer);
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId){
            Customer c = (customerRepository.save(customer));
        if (customerId == null){
            throw new ResourceNotFoundException("error updating account");
        }
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, c);
    }

    public ResponseEntity<?> deleteCustomerById(Long customerId){
            customerRepository.deleteById(customerId);
            return ResponseHandler.generateResponseNoObj("Customer deleted", HttpStatus.OK);
    }

public ResponseEntity<?> getCustomerByAccountId(Long accountId){
    Customer customer = customerRepository.findById(accountId).orElse(null);
    if (customer == null){
        throw new ResourceNotFoundException("error fetching customer");
    }
    return ResponseHandler.generateResponse("Successfully retrieved customer data!", HttpStatus.OK, customer);
}
}
