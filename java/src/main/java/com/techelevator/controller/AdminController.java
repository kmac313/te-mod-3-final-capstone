package com.techelevator.controller;

import com.techelevator.dao.CustomerDao;
import com.techelevator.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {
    private CustomerDao customerDao;

    public AdminController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomers = customerDao.getCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

}
