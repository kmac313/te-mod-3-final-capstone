package com.techelevator.dao;

import com.techelevator.model.Invoice;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;
import java.util.List;

public interface InvoiceDao {
    Invoice getInvoiceById(int id);

            //TODO Change invoice authroization to be based on role and not username

    List<Invoice> getInvoices(String from, String to, Principal principal);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);
    void createInvoiceProduct(int invoiceId, int productId);
    void deleteInvoiceById(int id);
    void deleteInvoicesByCustomerId(int id);
    public Invoice mapRowSet(SqlRowSet rowSet);
}
