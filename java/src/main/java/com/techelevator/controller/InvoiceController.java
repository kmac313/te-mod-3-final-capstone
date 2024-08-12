package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.PizzaDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Invoice;
import com.techelevator.model.Pizza;
import com.techelevator.model.Product;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;
@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class InvoiceController {
    private InvoiceDao invoiceDao;
    private ProductDao productDao;
    private PizzaDao pizzaDao;
    private UserDao userDao;

    public InvoiceController(InvoiceDao invoiceDao, ProductDao productDao, PizzaDao pizzaDao, UserDao userDao) {
        this.invoiceDao = invoiceDao;
        this.productDao = productDao;
        this.pizzaDao = pizzaDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.GET)
    //TODO BONUS Use requestParams to include all possible filters for invoices
    public ResponseEntity<List<Invoice>> getInvoices(@RequestParam(defaultValue = "0") String from,
                                                     @RequestParam(defaultValue = "0") String to,
                                                     @RequestParam(defaultValue = "all") String status, //TODO add filter for user_id
                                                     Principal principal) {

        List<Invoice> invoices = null;
        User user = userDao.getUserByUsername(principal.getName());
        invoices = invoiceDao.getInvoices(from, to, user);
        if (!status.equals("all")){
            List<String> statusList = new ArrayList<>();
            for (String s : status.split(",")){
                statusList.add(s);
            }
            List<Invoice> statusFilteredInvoices = new ArrayList<>();
            for (Invoice invoice : invoices){
                if(statusList.contains(invoice.getStatus())){
                    statusFilteredInvoices.add(invoice);
                }
            }
            invoices = statusFilteredInvoices;

        }
        return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }
    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getInvoiceById(@PathVariable int invoiceId, Principal principal) {
        int loggedInUserId = userDao.getUserByUsername(principal.getName()).getId();
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        List<Invoice>customerInvoices = invoiceDao.getInvoices("0","0",loggedInUser);
        List<Integer>customerInvoiceIds = new ArrayList<>();
        for(Invoice invoice : customerInvoices){
            customerInvoiceIds.add(invoice.getInvoiceId());
        }
        if(!customerInvoiceIds.contains(invoiceId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to view this" +
                    "invoice");
        }

        Invoice invoice = invoiceDao.getInvoiceById(invoiceId);
        List<Pizza> pizzas = pizzaDao.getPizzasByInvoiceId(invoiceId);
        List<Product> products = productDao.getProductsByInvoiceId(invoiceId);
        List<Product> filtered = productDao.filterOutPizzaProducts(products, pizzas);
        Map<String, Object> response = new HashMap<>();
        response.put("invoice", invoice);
        response.put("pizzas", pizzas);
        response.put("other", filtered);



        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<Invoice> createInvoice(@RequestBody Map<String, Object> placedOrder) {
        Invoice createdInvoice = null;
        Map<String, int[]> items = null;
        String creditCard = "";
        Boolean isDelivery = false;
        String address = "";
        List<Product> products = new ArrayList<>();
        List<List<Integer>> pizzasIntegerList = new ArrayList<>();
        List<Pizza> pizzas = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        creditCard = (String)placedOrder.get("credit_card");
        for (int c = 0 ; c <creditCard.length() ; c++){
            try {
                Integer.parseInt(creditCard.substring(c, c + 1));
                if (creditCard.length() != 16) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CC Number must be 16 digits");
                }
            } catch (NumberFormatException nfe) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-Numeric CC Number");
            }
        }
        isDelivery = (Boolean)placedOrder.get("is_delivery");
        address = (String)placedOrder.get("address");

        Invoice invoice = new Invoice();
        invoice.setTotal(BigDecimal.ZERO);
        invoice.setUserId(1);
        invoice.setDelivery(isDelivery);
        invoice.setStatus("Pending");
        createdInvoice = invoiceDao.createInvoice(invoice);

        /* Object Structure for the invoice
         {
            "items": {
                "pizza": [
                    [16,23,26,4,5,7],
                    [17,22,32,14],
                    [19,20,33,5,11,10]
                ],
                "other": [54,45,40,40,41,37,38]
            },
            "credit_card": "1234123412341234",
            "is_delivery": true,
            "address": "123 Main Street"
        }
        */

        items = (Map<String, int[]>)placedOrder.get("items");
        List<Integer> other = new ArrayList<>();
        for (Map.Entry entry : items.entrySet()) {
            switch (entry.getKey().toString()){
                case "other":
                    other = (List<Integer>) entry.getValue();
                    for (int productId : other) {
                        BigDecimal productPrice = productDao.getProductById(productId).getPrice();
                        createdInvoice.setTotal(createdInvoice.getTotal().add(productPrice));
                        invoiceDao.createInvoiceProduct(createdInvoice.getInvoiceId(), productId);
                    }
                    break;
                case "pizza":
                    for (List<Integer> pizza : (List<List<Integer>>)entry.getValue()){
                        pizzasIntegerList.add(pizza);

                        Pizza newPizza = new Pizza();
                        newPizza.setTotal(BigDecimal.ZERO);
                        newPizza.setPizzaName("");
                        newPizza.setAdditionalInstructions("");
                        newPizza.setInvoiceId(createdInvoice.getInvoiceId());

                        for (int productId : pizza) {
                            BigDecimal productPrice = productDao.getProductById(productId).getPrice();
                            newPizza.setTotal(newPizza.getTotal().add(productPrice));
                            Product nextProduct = productDao.getProductById(productId);
                            newPizza.addComponent(nextProduct);
                            invoiceDao.createInvoiceProduct(createdInvoice.getInvoiceId(), productId);
                        }
                        createdInvoice.setTotal(createdInvoice.getTotal().add(newPizza.getTotal()));
                        Pizza createdPizza = pizzaDao.createPizza(newPizza);
                        for (Product product: newPizza.getComponents()){
                            pizzaDao.createPizzaProduct(createdPizza.getPizzaId(), product.getProductId()
                            );
                        }
                    }
                    break;
            }
        }
        //TODO: Add authentication to ALL controllers


       //System.out.println(items + "\n" + creditCard + "\n" + isDelivery + "\n" + address);

        return new ResponseEntity<Invoice>(invoiceDao.updateInvoice(createdInvoice), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/invoices/", method = RequestMethod.PUT)
    public ResponseEntity<Invoice>updateInvoice (@RequestBody Invoice invoice,  Principal principal) {
        int invoiceId = invoice.getInvoiceId();
        int loggedInUserId = userDao.getUserByUsername(principal.getName()).getId();
        User loggedInUser = userDao.getUserByUsername(principal.getName());
        List<Invoice>customerInvoices = invoiceDao.getInvoices("0","0",loggedInUser);
        List<Integer>customerInvoiceIds = new ArrayList<>();
        for(Invoice i : customerInvoices){
            customerInvoiceIds.add(i.getInvoiceId());
        }
        if(!customerInvoiceIds.contains(invoiceId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update this" +
                    "invoice");
        }

        Invoice updatedInvoice = null;
        try {
            updatedInvoice = invoiceDao.updateInvoice(invoice);
        } catch(DaoException doe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
        }
        return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int invoiceId) {
        invoiceDao.deleteInvoiceById(invoiceId);
    }
}
