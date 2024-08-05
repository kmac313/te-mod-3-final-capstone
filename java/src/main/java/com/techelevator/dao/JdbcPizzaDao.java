package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pizza;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPizzaDao implements PizzaDao{
    private final JdbcTemplate db;

    public JdbcPizzaDao(JdbcTemplate jdbcTemplate){
        this.db = jdbcTemplate;
    }
    @Override
    public List<Pizza> getPizzas() {
        String sql = "SELECT p.productId, p.price, p.description, pi.pizzaId, pi.invoiceId, pi.sizeId," +
                "pi.pizza_name, pi.crust, pi.sauce, pi.total, pi.additionalInstructions FROM pizza pi " +
                "JOIN product p ON pi.product_id = p.product_id" +
                "ORDER BY pi.pizza_id";
        List<Pizza> allPizzas = new ArrayList<>();

        try {
            SqlRowSet results = db.queryForRowSet(sql);
            while(results.next()){
                allPizzas.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allPizzas;
    }

    @Override
    public Pizza getPizzaById(int id) {
        String sql = "SELECT p.productId, p.price, p.description, pi.pizzaId, pi.invoiceId, pi.sizeId, " +
                "pi.pizza_name, pi.crust, pi.sauce, pi.total, pi.additionalInstructions FROM pizza pi " +
                "JOIN product p ON pi.product_id = p.product_id WHERE pi.pizza_id = ?";
        Pizza pizza = null;
        try {
            SqlRowSet result = db.queryForRowSet(sql, id);
            if (result.next()){
                pizza = mapRowSet(result);
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pizza;
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        String sql = "INSERT INTO pizza (invoice_id, product_id, size_id, pizza_name, crust, sauce, price, additional_instructions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING pizza_id";
        int createdPizzaId = 0;

        try {
            createdPizzaId = db.queryForObject(sql, int.class,
                    pizza.getInvoiceId(), pizza.getProductId(), pizza.getSizeId(), pizza.getPizzaName(), pizza.getCrust(),
                    pizza.getSauce(), pizza.getPrice(), pizza.getAdditionalInstructions());
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getPizzaById(createdPizzaId);
    }

    @Override
    public Pizza updatePizza(Pizza pizza) {
        String sql = "UPDATE pizza " +
                "SET product_id, size_id, pizza_name, crust, sauce, price, additional_instructions" +
                "WHERE pizza_id = ?";
        int numRowsAffected = 0;
        try {
            numRowsAffected = db.update(sql, pizza.getProductId(), pizza.getSizeId(), pizza.getCrust(), pizza.getSauce(),
                    pizza.getPrice(), pizza.getAdditionalInstructions());
            if (numRowsAffected == 0){
                throw new DaoException("No matching Pizza found, check Pizza ID");
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    @Override
    public void deletePizzaById(int id) {
        String sql = "DELETE FROM pizza WHERE pizza_id = ?";
        try {
            db.update(sql, id);
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

    }

    @Override
    public Pizza mapRowSet(SqlRowSet rowSet) {
        return new Pizza(
                rowSet.getInt("p.product_id"),
                rowSet.getBigDecimal("p.price"),
                rowSet.getString("p.description"),
                rowSet.getInt("pi.pizza_id"),
                rowSet.getInt("pi.invoice_id"),
                rowSet.getString("pi.size_id"),
                rowSet.getString("pi.pizza_name"),
                rowSet.getString("pi.crust"),
                rowSet.getString("pi.sauce"),
                rowSet.getBigDecimal("total"),
                rowSet.getString("additional_instructions")
                );


    }
}
