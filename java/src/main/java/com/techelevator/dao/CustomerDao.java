package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;
import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(int id);
    void deleteCustomerByUsername(String username);
    public Customer mapRowSet(SqlRowSet rowSet);
}
