package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer updatedCustomer);
    void deleteCustomerById(int id);
    void deleteCustomerByUsername(String username);
}
