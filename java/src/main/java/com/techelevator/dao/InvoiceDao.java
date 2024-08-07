package com.techelevator.dao;

import com.techelevator.model.Invoice;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface InvoiceDao {
    List<Invoice> getInvoices();
    Invoice getInvoiceById(int id);
    List<Invoice> getInvoicesByCustomerId(int id);
    List<Invoice> getInvoicesFromDateRange(String from, String to);
    Invoice createInvoice(Invoice invoice);
    //TODO define updateInvoice method
    void deleteInvoiceById(int id);
    void deleteInvoicesByCustomerId(int id);
    public Invoice mapRowSet(SqlRowSet rowSet);
}
