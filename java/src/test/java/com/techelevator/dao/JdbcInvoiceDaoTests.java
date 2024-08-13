package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Invoice;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.apache.tomcat.jni.Time;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class JdbcInvoiceDaoTests extends BaseDaoTests {

    static Timestamp timestamp = Timestamp.valueOf(("2022-01-15 10:30:00"));

    protected static final Invoice INVOICE_1 = new Invoice(1, 3, BigDecimal.valueOf(150.75), true, "Pending", timestamp);



    private JdbcInvoiceDao sut;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcInvoiceDao(jdbcTemplate);
    }

    @Test
    public void getInvoiceById_given_invalid_invoice_id_returns_null() {
        Invoice actualInvoice = sut.getInvoiceById(-1);

        Assert.assertNull(actualInvoice);
    }

    @Test
    public void getInvoiceById_given_valid_invoice_id_returns_invoice(){
        Invoice actualInvoice = sut.getInvoiceById(INVOICE_1.getInvoiceId());

        Assert.assertEquals(INVOICE_1, actualInvoice);
    }

//    @Test
//    public void getInvoices_returns_all_invoices(){
//        List<Invoice> invoices = sut.getInvoices(1,2, "user1")
//
//        Assert.assertNotNull(users);
//        Assert.assertEquals(3, users.size());
//        Assert.assertEquals(USER_1, users.get(0));
//        Assert.assertEquals(USER_2, users.get(1));
//        Assert.assertEquals(USER_3, users.get(2));
//    }
}