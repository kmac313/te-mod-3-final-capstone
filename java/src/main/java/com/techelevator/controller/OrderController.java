package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.model.Invoice;
import com.techelevator.model.Product;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.ReadPendingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    private InvoiceDao invoiceDao;
    private ProductDao productDao;

    public OrderController(InvoiceDao invoiceDao, ProductDao productDao){
        this.invoiceDao = invoiceDao;
        this.productDao = productDao;
    }

    //TODO get invoices from specified range, all if range not specified. GET
    @RequestMapping(path = "/invoices", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> getInvoices(DateTimeFormat from, DateTimeFormat to){
        return null;
    }
    //TODO create/add Invoice, POST
    //TODO ResponseStatus.CREATED
    public void createInvoice(Invoice invoice){

    }
    //TODO update/modify existing Invoice, PUT
    // TODO ResponseStatus.CREATED
    public void updateInvoice (Invoice invoice){

    }

    //TODO delete existing invoice, DELETE
    public void deleteInvoice (int invoiceId){

    }
    @RequestMapping (path = "/menu", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Product>>> getMenu(){
        List<Product> products = productDao.getProducts();
        Map<String, List<Product>> menuObject = new HashMap<>();
        Map<Integer, String> categories = productDao.getCategories();
        for (String category: categories.values()){
            menuObject.put(category, new ArrayList<>());
        }
        for (Product product: products){
            menuObject.get(product.getProductCategoryDescription()).add(product);
        }
        return new ResponseEntity<>(menuObject, HttpStatus.OK);
    }
    //TODO send front-end products that make up a pizza, GET
    @RequestMapping(path = "/menu/pizza", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getPizzaMenu(){
        List<String> pizzaComponentCategories = new ArrayList<>();
        String[] categories = new String[] {"Size", "Crust", "Sauce","Regular Topping", "Premium Topping"};
        for (String category : categories){
            pizzaComponentCategories.add(category);
        }
        System.out.println(pizzaComponentCategories);
        List<Product> pizzaComponents = productDao.getProductsByCategoryDescription(pizzaComponentCategories);
        return new ResponseEntity<>(pizzaComponents, HttpStatus.OK);

    }
}
