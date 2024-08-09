package com.techelevator.controller;

import com.techelevator.dao.CustomerDao;
import com.techelevator.model.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    private CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //TODO Get Customer Method

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerDao.getCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //TODO Get Customer By Id Method

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomersById(@PathVariable int id){
        return new ResponseEntity<Customer>(customerDao.getCustomerById(id), HttpStatus.OK);
    }

    //TODO Get Customer By Email Method

    @RequestMapping(path = "/customer/{email}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomersByEmail(@RequestBody String email){
        return new ResponseEntity<Customer>(customerDao.getCustomerByEmail(email), HttpStatus.OK);
    }

    //TODO Create Customer Method

    //TODO Update Customer Method

    //TODO Delete Customer By Id Method

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {
        customerDao.deleteCustomerById(id);
    }

    //TODO Delete Customer By Username Method

    @RequestMapping(path = "/customer/{username}", method = RequestMethod.DELETE)
    public void deleteCustomerByUsername(@PathVariable String username) {
        customerDao.deleteCustomerByUsername(username);
    }

    //TODO Customer MapRowSet Method








}
