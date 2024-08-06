package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Product> getProducts();
    Map<Integer,String> getCategories();

    Product getProductById(int productId);
    List<Product> getProductsByCategoryIds(int[] categoryIds);
    List<Product> getProductsByCategoryDescription(List<String> categoryDescriptions);
    Product addProduct(Product product);
    void deleteProductById(int productId);
    Product updateProduct (Product product);

    Product mapRowSet (SqlRowSet rowSet);
}
