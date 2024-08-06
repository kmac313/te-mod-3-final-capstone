package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.model.Invoice;
import com.techelevator.model.Product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class InvoiceController {
    private InvoiceDao invoiceDao;

    public InvoiceController(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    //TODO get invoices from specified range, all if range not specified. GET
    @RequestMapping(path = "/invoices", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> getInvoices(@RequestParam(defaultValue = "1971-01-01") String from,
                                                     @RequestParam(defaultValue = "9999-12-31") String to) {
        List<Invoice> invoices = null;
        invoices = invoiceDao.getInvoicesFromDateRange(from, to);
//        System.out.println(from + " - " + to);
        return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }

    //TODO create/add Invoice, POST
    //TODO ResponseStatus.CREATED
    @RequestMapping(path = "/invoice", method = RequestMethod.POST)
    public ResponseEntity<Invoice> createInvoice(@RequestBody Map<String, Object> placedOrder) {
        Invoice createdInvoice = null;
        List<Product> products = null;
        String creditCard = "";
        Boolean isDelivery = false;
        String address = "";

        //TODO: Use Spring to serialize products
        products = (List<Product>)placedOrder.get("products");
        creditCard = (String)placedOrder.get("credit_card");
        isDelivery = (Boolean)placedOrder.get("is_delivery");
        address = (String)placedOrder.get("address");

        System.out.println(products + "\n" + creditCard + "\n" + isDelivery + "\n" + address);
        System.out.println(products.get(0) instanceof Product);
        return new ResponseEntity<Invoice>(new Invoice(0,0,BigDecimal.ZERO,true, false,
                new Timestamp(2024)), HttpStatus.CREATED);
    }

    //TODO update/modify existing Invoice, PUT
    // TODO ResponseStatus.CREATED
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/invoice/{invoiceId}", method = RequestMethod.PUT)
    public void updateInvoice(@RequestBody Invoice invoice,  @PathVariable int invoiceId) {
        //TODO: Implement updateInvoice method to DAO
    }

    //TODO delete existing invoice, DELETE
    @RequestMapping(path = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int invoiceId) {
        invoiceDao.deleteInvoiceById(invoiceId);
    }

    @RequestMapping(path = "/delivery", method = RequestMethod.POST)
    public ResponseEntity<Boolean> validateDelivery(@RequestBody String address) {
        //TODO: Integrate Mapbox API
        return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }
}
