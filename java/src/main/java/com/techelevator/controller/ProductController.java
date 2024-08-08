package com.techelevator.controller;

import com.techelevator.dao.InvoiceDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.model.Invoice;
import com.techelevator.model.Product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ProductController {
    private ProductDao productDao;


    public ProductController(ProductDao productDao) {
        this.productDao = productDao;

    }

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Product>>> getMenu() {
        List<Product> products = productDao.getProducts();
        Map<String, List<Product>> menuObject = new HashMap<>();
        Map<Integer, String> categories = productDao.getCategories();
        for (String category : categories.values()) {
            menuObject.put(category, new ArrayList<>());
        }
        for (Product product : products) {
            menuObject.get(product.getProductCategoryDescription()).add(product);
        }
        return new ResponseEntity<>(menuObject, HttpStatus.OK);
    }


    @RequestMapping(path = "/menu/pizza", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Product>>> getPizzaMenu(Principal principal) {
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

    //TODO method for getting products by requested categories (maybe add to the above function)


}
