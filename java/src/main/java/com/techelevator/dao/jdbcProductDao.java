package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class jdbcProductDao implements ProductDao{
    private JdbcTemplate db;
    private NamedParameterJdbcTemplate namedDb;

    public jdbcProductDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template) {
        this.db = jdbcTemplate;
        this.namedDb = template;
    }

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT p.product_id, p.product_category_id, pc.product_category_description, " +
                "p.price, p.description, p.quantity " +
                "FROM product p " +
                "JOIN product_category pc ON p.product_category_id = pc.product_category_id " +
                "ORDER BY p.product_category_id";
      List<Product> allProducts = new ArrayList<>();
        try{
            SqlRowSet results = db.queryForRowSet(sql);
            while(results.next()){
                allProducts.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allProducts;
    }

    @Override
    public Map<Integer, String> getCategories() {
        String sql = "SELECT product_category_id, product_category_description FROM product_category " +
                "ORDER BY product_category_id";
        Map<Integer, String> categories = new HashMap<>();
        try{
            SqlRowSet results = db.queryForRowSet(sql);
            while(results.next()){
                categories.put(
                        results.getInt("product_category_id"),
                        results.getString("product_category_description"));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return categories;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT p.product_id, p.product_category_id, pc.product_category_description, price, description, quantity FROM product p " +
                "JOIN product_category pc ON p.product_category_id = pc.product_category_id " +
                "WHERE p.product_id = ?";
        try{
            SqlRowSet result = db.queryForRowSet(sql, productId);
            if(result.next()){
                product = mapRowSet(result);
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByInvoiceId(int invoiceId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_category_id, pc.product_category_description, " +
                "p.price, p.description, p.quantity " +
                "FROM product p " +
                "JOIN product_category pc ON p.product_category_id = pc.product_category_id " +
                "JOIN invoice_product ip ON p.product_id = ip.product_id " +
                "WHERE ip.invoice_id = ? " +
                "ORDER BY p.product_id ";
        try{
            SqlRowSet results = db.queryForRowSet(sql, invoiceId);
            while(results.next()){
                products.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryIds(int[] categoryIds) {
        List<Product> categoryProducts = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_category_id, pc.product_category_description, " +
                "p.price, p.description, p.quantity " +
                "FROM product p " +
                "JOIN product_category pc ON p.product_category_id = pc.product_category_id " +
                "WHERE pc.product_category_id IN (:ids) " +
                "ORDER BY p.product_category_id";
        try{
            SqlParameterSource parameters = new MapSqlParameterSource("ids", categoryIds);
            SqlRowSet results = namedDb.queryForRowSet(sql, parameters);
            while(results.next()) {
                categoryProducts.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return categoryProducts;
    }

    @Override
    public List<Product> getProductsByCategoryDescription(List<String> categoryDescriptions) {
        List<Product> categoryProducts = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_category_id, pc.product_category_description, " +
                "p.price, p.description, p.quantity " +
                "FROM product p " +
                "JOIN product_category pc ON p.product_category_id = pc.product_category_id " +
                "WHERE pc.product_category_description IN (:ids) " +
                "ORDER BY p.product_category_id";

        try{
            SqlParameterSource parameters = new MapSqlParameterSource("ids", categoryDescriptions);
            SqlRowSet results = namedDb.queryForRowSet(sql, parameters);
            while(results.next()){
                categoryProducts.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return categoryProducts;
    }

    @Override
    public Product addProduct(Product product) {
        String sql = "INSERT INTO product (product_category_id, price, description, quantity) " +
                "VALUES (?, ?, ?, ?) RETURNING product_id";
        int createdProductId = 0;

        try{
            createdProductId=db.queryForObject(sql,int.class,
                    product.getProductCategoryId(), product.getPrice(), product.getDescription(), product.getQuantity()
                    );
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getProductById(createdProductId);
    }

    @Override
    public void deleteProductById(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try{
            db.update(sql, productId);
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        String sql = "UPDATE product SET product_category_id = ?, price = ?, description = ?, quantity = ?" +
                "WHERE product_id = ?";
        int numRowsAffected = 0;
        Product updatedProduct = null;

        try{
            numRowsAffected=db.update(sql,
                    product.getProductCategoryId(), product.getPrice(), product.getDescription(), product.getQuantity(),
                    product.getProductId()
            );
            if (numRowsAffected == 0 ){
                throw new DaoException("Product Not Found, Check Product Id");
            } else{
                updatedProduct = getProductById(product.getProductId());
            }

        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return updatedProduct;

    }

    @Override
    public Product mapRowSet(SqlRowSet rowSet) {
        return new Product(
                rowSet.getInt("product_id"),
                rowSet.getInt("product_category_id"),
                rowSet.getString("product_category_description"),
                rowSet.getBigDecimal("price"),
                rowSet.getString("description"),
                rowSet.getInt("quantity")
        );
    }
}
