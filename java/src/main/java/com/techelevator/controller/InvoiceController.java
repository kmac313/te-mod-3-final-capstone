package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Invoice;
import com.techelevator.model.Product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class InvoiceController {
    private InvoiceDao invoiceDao;

    public InvoiceController(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> getInvoices(@RequestParam(defaultValue = "1971-01-01") String from,
                                                     @RequestParam(defaultValue = "9999-12-31") String to) {
        List<Invoice> invoices = null;
        invoices = invoiceDao.getInvoicesFromDateRange(from, to);
//        System.out.println(from + " - " + to);
        return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }
    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable int invoiceId) {
        //TODO: Add a try catch argument to catch users trying to use a string as the id
        return new ResponseEntity<Invoice>(invoiceDao.getInvoiceById(invoiceId), HttpStatus.OK);
    }


    @RequestMapping(path = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<Invoice> createInvoice(@RequestBody Map<String, Object> placedOrder) {
        Invoice createdInvoice = null;
        Map<String, int[]> items = null;
        String creditCard = "";
        Boolean isDelivery = false;
        String address = "";
        List<Integer> products = new ArrayList<>();
        List<List<Integer>> pizzas = new ArrayList<>();

        /* Object Structure for the invoice
        {
            items: {
                pizza: [
                    [1,2,3],[1,3,4],[1,5,6]
                ],
                other: [76,46,73,9]
            },
            creditcard: "123456789235",
            isDelivery: true,
            address: "123 Main Street"
        }
        */

        items = (Map<String, int[]>)placedOrder.get("items");
        List<Integer> other = new ArrayList<>();
        for (Map.Entry entry : items.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().getClass());
            switch (entry.getKey().toString()){
                case "other":
                    other = (List<Integer>) entry.getValue();
                break;

                case "pizza":
                    for (List<Integer> pizza : (List<List<Integer>>)entry.getValue()){
                        pizzas.add(pizza);
                }
                    System.out.println(pizzas);
            }
        }
        for (int product_id : other) {
            products.add(product_id);
        }
        System.out.println(products);
//        for (int pizza : items.get("pizza")) {
//
//        }
        creditCard = (String)placedOrder.get("credit_card");
        isDelivery = (Boolean)placedOrder.get("is_delivery");
        address = (String)placedOrder.get("address");

        //TODO update/create pizza databases to reflect new order
        //TODO update/create pizza_product databases to reflect new order
        //TODO update/create invoice databases to reflect new order
        //TODO update/create invoice_product databases to reflect new order

       System.out.println(items + "\n" + creditCard + "\n" + isDelivery + "\n" + address);

        return new ResponseEntity<Invoice>(new Invoice(0,0,BigDecimal.ZERO,false, false,
                new Timestamp(2024)), HttpStatus.CREATED);
    }

    //TODO update/modify existing Invoice, PUT
    // TODO ResponseStatus.CREATED
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.PUT)
    public ResponseEntity<Invoice>updateInvoice (@RequestBody Invoice invoice,  @PathVariable int invoiceId) {
        invoice.setInvoiceId(invoiceId);
        Invoice updatedInvoice = null;
        try {
            updatedInvoice = invoiceDao.updateInvoice(invoice);
        } catch(DaoException doe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
        }
        return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
    }


    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int invoiceId) {
        invoiceDao.deleteInvoiceById(invoiceId);
    }

    @RequestMapping(path = "/delivery", method = RequestMethod.POST)
    public ResponseEntity<Boolean> validateDelivery(@RequestBody String address) {
        //TODO: Integrate Mapbox API
        return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }
}
