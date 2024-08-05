package com.techelevator.dao;

import com.techelevator.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCustomerDao implements CustomerDao{
    private final JdbcTemplate db;
    private final String SELECT_SQL_BASE = "SELECT customer_id, first_name, last_name, street_address, city, zip_code, " +
            " state_abbreviation, phone_number, email, username FROM customer ";

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.db = jdbcTemplate;
    }
    @Override
    public List<Customer> getCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        String sql = SELECT_SQL_BASE;
        SqlRowSet results = db.queryForRowSet(sql);
        while (results.next()){
            allCustomers.add(mapRowSet(results));
        }
        return allCustomers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = SELECT_SQL_BASE + "WHERE customer_id  = ?";
        SqlRowSet result = db.queryForRowSet(sql, id);
        if (result.next()){
            customer = mapRowSet(result);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        return null;
    }

    @Override
    public void deleteCustomerById(int id) {

    }

    @Override
    public void deleteCustomerByUsername(String username) {

    }

    @Override
    public Customer mapRowSet(SqlRowSet rowSet){
        Customer mappedCustomer = new Customer(
                rowSet.getInt("customer_id"),
                rowSet.getString("first_name"),
                rowSet.getString("last_name"),
                rowSet.getString("street_address"),
                rowSet.getString("city"),
                rowSet.getInt("zip_code"),
                rowSet.getString("state_abbreviation"),
                rowSet.getString("phone_number"),
                rowSet.getString("email"),
                rowSet.getString("username")
        );
        return mappedCustomer;
    }
}

