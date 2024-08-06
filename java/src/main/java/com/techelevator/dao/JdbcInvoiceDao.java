package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Invoice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcInvoiceDao implements InvoiceDao{
    private final JdbcTemplate db;

    public JdbcInvoiceDao(JdbcTemplate jdbcTemplate) {
        this.db = jdbcTemplate;
    }

    @Override
    public List<Invoice> getInvoices() {
        String sql = "SELECT invoice_id, customer_id, total, is_delivery FROM invoice ORDER BY invoice_id";
        List<Invoice> allInvoices = new ArrayList<>();

        try {
            SqlRowSet results = db.queryForRowSet(sql);
            while(results.next()){
                allInvoices.add(mapRowSet(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return allInvoices;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        String sql = "SELECT invoice_id, customer_id, total, is_delivery FROM invoice" +
                "WHERE invoice_id = ?";
        Invoice invoice = null;
        try{
            SqlRowSet result = db.queryForRowSet(sql, id);
            if(result.next()){
                invoice = mapRowSet(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return invoice;
    }

    @Override
    public List<Invoice> getInvoicesByCustomerId(int id) {
        String sql = "SELECT invoice_id, customer_id, total, is_delivery FROM invoice" +
                "WHERE customer_id = ?" +
                " ORDER BY invoice_id";
        List<Invoice> customerInvoices = new ArrayList<>();
        try{
            SqlRowSet results = db.queryForRowSet(sql);
            while (results.next()){
                customerInvoices.add(mapRowSet(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return customerInvoices;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice (customer_id, total, is_delivery) " +
                "VALUES (?, ?, ?) RETURNING invoice_id";
        int createdInvoiceId;
        try{
             createdInvoiceId = db.queryForObject(sql, int.class,
                    invoice.getCustomerId(), invoice.getTotal(), invoice.isDelivery());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getInvoiceById(createdInvoiceId);
    }

    //TODO Maybe we SHOULD update invoices?

    @Override
    public void deleteInvoiceById(int id) {
        String sql = "DELETE FROM invoice WHERE invoice_id = ?";
        try{
            db.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public void deleteInvoicesByCustomerId(int id) {
        String sql = "DELETE FROM invoice WHERE customer_id = ?";
        try{
            db.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public Invoice mapRowSet(SqlRowSet rowSet) {
        return new Invoice(
                rowSet.getInt("invoice_id"),
                rowSet.getInt("customer_id"),
                rowSet.getBigDecimal("total"),
                rowSet.getBoolean("is_delivery")
        );
    }
}