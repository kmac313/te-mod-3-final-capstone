package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.Invoice;
import com.techelevator.model.Product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProductController {
    private ProductDao productDao;


    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/menu", method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<Product>>> getMenu(@RequestHeader Map<String, String> header,
                                                              @RequestBody Map<String, List<String>> requestObject) {
        List<String> requestedCategories = requestObject.get("categories");
        List<Product> products = new ArrayList<>();
        Map<String, List<Product>> menuObject = new HashMap<>();
        Map<Integer, String> categories = new HashMap<>();
        if (requestedCategories.size() != 0){
            products = productDao.getProductsByCategoryDescription(requestedCategories);
            for (String category: requestedCategories){
                menuObject.put(category, new ArrayList<>());
            }
        } else {
            categories =  productDao.getCategories();
            products = productDao.getProducts();
            for (String category : categories.values()) {
                menuObject.put(category, new ArrayList<>());
            }
        }

        for (Product product : products) {
            menuObject.get(product.getProductCategoryDescription()).add(product);
        }
        return new ResponseEntity<>(menuObject, HttpStatus.OK);
    }
    //TODO change requestbody for things to requestparams that is a CSV. example ?categories=Drink,Salad,Dessert
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/menu/pizza", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Product>>> getPizzaMenu() {
        List<String> pizzaComponentCategories = new ArrayList<>();
        String[] categories = new String[]{"Size", "Crust", "Sauce", "Regular Topping", "Premium Topping"};
        Map<String, List<Product>> pizzaComponents = new HashMap<>();
        for (String category : categories) {
            List<String> tempList = new ArrayList<>();
            tempList.add(category);
            pizzaComponents.put(category,
                    productDao.getProductsByCategoryDescription(tempList));
        }

        return new ResponseEntity<>(pizzaComponents, HttpStatus.OK);

    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/menu/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        return new ResponseEntity<Product>(productDao.getProductById(productId), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/menu/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int productId) {
        productDao.deleteProductById(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/product", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Map<String, Object> newProduct) {
        Product createdProduct;
         /* Object Structure for the product
         {
            "productId" : null
            "productCategoryDescription" : "Drink"
            "price" : 0.75
            "description" : "Extra Cheese"
            "quantity" : 10
        }
        */

        String productCategoryDescription = (String)newProduct.get("product_category_description");
        BigDecimal price = BigDecimal.valueOf((Double)newProduct.get("price"));
        String description = (String)newProduct.get("description");
        int quantity = (int)newProduct.get("quantity");

        Product product = new Product();
        product.setProductCategoryDescription(productCategoryDescription);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);
        createdProduct = productDao.addProduct(product);
        //TODO make sure all json objects are in snake case from front end
        return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/menu/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Product>updateProduct (@RequestBody Product product,  @PathVariable int productId) {
        System.out.println(product.getDescription());
        System.out.println(product.getPrice());
        product.setProductId(productId);
        Product updatedProduct = null;
        try {
            updatedProduct = productDao.updateProduct(product);
        } catch(DaoException doe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }
    }
    //TODO with front end present, enforce logical consistency across endpoint paths.