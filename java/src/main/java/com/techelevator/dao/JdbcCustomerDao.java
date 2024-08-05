package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
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
        try {
            SqlRowSet results = db.queryForRowSet(sql);
            while (results.next()){
                allCustomers.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the list of customers");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allCustomers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = SELECT_SQL_BASE + "WHERE customer_id  = ?";
        try {
            SqlRowSet result = db.queryForRowSet(sql, id);
            if (result.next()){
                customer = mapRowSet(result);
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the customer by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        String sql = SELECT_SQL_BASE + "WHERE email = ?";
        try {
            SqlRowSet result = db.queryForRowSet(sql, email);
            if (result.next()) {
                customer = mapRowSet(result);
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the customer by email");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        int newCustomerId = 0;
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, street_address, city, " +
                "zip_code, state_abbreviation, phone_number, email, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING customer_id";
        try {
            newCustomerId = db.queryForObject(sql, int.class,
                    customer.getFirstName(), customer.getLastName(), customer.getStreetAddress(),
                    customer.getCity(), customer.getZipcode(), customer.getStateAbbreviation(), customer.getPhoneNumber(),
                    customer.getEmail(), customer.getUsername());

        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened adding the customer to DB");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (NullPointerException np){
            System.out.println("Null Pointer Exception");
        }

        return getCustomerById(newCustomerId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer updatedCustomer = null;
        String sql = "UPDATE customer " +
                "SET first_name = ?, last_name = ?, street_address = ?, city = ?, zip_code = ?, " +
                "state_abbreviation = ?, phone_number = ?, email = ?, username = ? " +
                "WHERE customer_id = ?";
        try {
            int rowsAffected = db.update(sql, customer.getFirstName(), customer.getLastName(), customer.getStreetAddress(),
                    customer.getCity(), customer.getZipcode(), customer.getStateAbbreviation(), customer.getPhoneNumber(),
                    customer.getEmail(), customer.getUsername(), customer.getCustomerId());

            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedCustomer = getCustomerById(customer.getCustomerId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCustomer;
    }

    @Override
    public void deleteCustomerById(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try {
            db.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteCustomerByUsername(String username) {
        String sql = "DELETE FROM customer WHERE username = ?";
        try {
            db.update(sql, username);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
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

