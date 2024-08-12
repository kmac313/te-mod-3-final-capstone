package com.techelevator.dao;

import com.techelevator.model.Invoice;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;
import java.util.List;

public interface InvoiceDao {
    Invoice getInvoiceById(int id);
    List<Invoice> getInvoicesByCustomerId(int id);

    //TODO Change invoice authorization to be based on role and not username

    List<Invoice> getInvoices(String from, String to, User user);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);

    List<Invoice> getInvoiceByStatus(String status);
    void createInvoiceProduct(int invoiceId, int productId);
    void deleteInvoiceById(int id);
    void deleteInvoicesByCustomerId(int id);
    public Invoice mapRowSet(SqlRowSet rowSet);
}
