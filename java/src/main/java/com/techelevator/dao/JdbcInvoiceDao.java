package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Invoice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcInvoiceDao implements InvoiceDao{
    private final JdbcTemplate db;

    public JdbcInvoiceDao(JdbcTemplate jdbcTemplate) {
        this.db = jdbcTemplate;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        String sql = "SELECT invoice_id, customer_id, total, is_delivery, is_complete, timestamp FROM invoice " +
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
        String sql = "SELECT invoice_id, customer_id, total, is_delivery, is_complete, timestamp FROM invoice" +
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
    public List<Invoice> getInvoices(String from, String to, Principal principal) {
        String username = principal.getName();
        System.out.println(username);
        List<Invoice> invoices = new ArrayList<>();
        List<Integer> fromInts = new ArrayList<>();
        List<Integer> toInts = new ArrayList<>();
        String sql = "SELECT invoice_id, invoice.customer_id, total, is_delivery, is_complete, timestamp, username" +
                " FROM invoice " +
                "JOIN customer c ON invoice.customer_id = c.customer_id ";
        if(!from.equals("0")) {
            for (String s : from.split("-")) {
                fromInts.add(Integer.parseInt(s));
            }
        }
        if (!to.equals("0")) {
            for (String s : to.split("-")) {
                toInts.add(Integer.parseInt(s));
            }
        }

        SqlRowSet results = null;
        try{

            if(!from.equals("0") && !to.equals("0")){
                sql+="WHERE timestamp BETWEEN make_date(?,?,?) AND make_date(?,?,?) ";
                results = db.queryForRowSet(sql,
                        fromInts.get(0),fromInts.get(1),fromInts.get(2),
                        toInts.get(0), toInts.get(1), toInts.get(2));
            } else if (!from.equals("0") && to.equals("0")) {
                sql += "WHERE timestamp >= make_date(?,?,?) ";
                results = db.queryForRowSet(sql,
                        fromInts.get(0),fromInts.get(1),fromInts.get(2));
            }else if (from.equals("0") && !to.equals("0")){
                sql += "WHERE timestamp <= make_date(?,?,?) ";
                results = db.queryForRowSet(sql,
                        toInts.get(0), toInts.get(1), toInts.get(2));
            } else {
                results = db.queryForRowSet(sql);
            }

            while(results.next()) {
                if (username.equals("admin")){
                    invoices.add(mapRowSet(results));
                } else {
                    if(results.getString("username").equals(username)){
                        invoices.add(mapRowSet(results));
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return invoices;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice (customer_id, total, is_delivery, is_complete) " +
                "VALUES (?, ?, ?, ?) RETURNING invoice_id";
        int createdInvoiceId;
        try{
             createdInvoiceId = db.queryForObject(sql, int.class,
                    invoice.getCustomerId(), invoice.getTotal(), invoice.isDelivery(), invoice.isComplete());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getInvoiceById(createdInvoiceId);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoice " +
                "SET customer_id = ?, total = ?, is_delivery = ?, is_complete = ?, timestamp = ? " +
                "WHERE invoice_id = ?";
        int numRowsAffected = 0;
        try {
            numRowsAffected = db.update(sql, invoice.getCustomerId(), invoice.getTotal(),
                    invoice.isDelivery(), invoice.isComplete(), invoice.getTimestamp(), invoice.getInvoiceId());
            if (numRowsAffected == 0){
                throw new DaoException("No matching Invoice found, check Invoice ID");
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the invoice by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getInvoiceById(invoice.getInvoiceId());
    }

    @Override
    public void createInvoiceProduct(int invoiceId, int productId) {
        String sql = "INSERT INTO invoice_product (invoice_id, product_id) " +
                "VALUES (?,?) ";
        try{
            db.update(sql, invoiceId, productId);
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the invoice by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

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
                rowSet.getBoolean("is_delivery"),
                rowSet.getBoolean("is_complete"),
                rowSet.getTimestamp("timestamp")
        );
    }
}
