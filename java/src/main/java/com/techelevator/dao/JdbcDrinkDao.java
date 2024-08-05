package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Drink;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDrinkDao implements DrinkDao{
    private final JdbcTemplate db;

    public JdbcDrinkDao(JdbcTemplate jdbcTemplate) {
        this.db = jdbcTemplate;
    }

    public List<Drink> getDrinks() {
        List<Drink> drinks = new ArrayList<>();
        String sql = "SELECT p.product_id, p.price, p.description, d.drink_id, " +
                "d.drink_size_id, d.invoice_id, d.flavor FROM drink d " +
                "JOIN product p" +
                "ON p.product_id = d.product_id ORDER BY p.description";
        try {
            SqlRowSet results = db.queryForRowSet(sql);
            while(results.next()) {
                drinks.add(mapRowSet(results));
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drinks");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drinks;
    }

    @Override
    public Drink getDrinkByDrinkId(int id) {
        Drink drink = null;
        String sql = "SELECT p.product_id, p.price, p.description, d.drink_id, " +
                "d.drink_size_id, d.invoice_id, d.flavor FROM drink d " +
                "JOIN product p" +
                "ON p.product_id = d.product_id" +
                "WHERE d.drink_id = ?";
        try {
            SqlRowSet result = db.queryForRowSet(sql, id);
            if (result.next()) {
                drink = mapRowSet(result);
            }
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return drink;
    }

    @Override
    public Drink createDrink(Drink drink) {
        int createdProductId = 0;
        int createdDrinkId = 0;
        String productSql = "INSERT INTO product (product_id, price, description) " +
                "VALUES (?, ?, ?)";
        String drinkSql = "INSERT INTO drink (drink_id, drink_size_id, invoice_id, product_id, flavor) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING drink_id";
        //TODO: Finish sql statements and logic
        try {
            createdProductId = db.queryForObject(productSql, int.class,
                    drink.getProductId(), drink.getPrice(), drink.getDescription());
            createdDrinkId = db.queryForObject(drinkSql, int.class,
                    drink.getDrinkId(), drink.getDrinkSizeId(), drink.getInvoiceId(), drink.getProductId(), drink.getFlavor());
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getDrinkByDrinkId(createdDrinkId);
    }

    @Override
    public Drink updateDrink(Drink drink) {
        String updateProductSql = "UPDATE product SET price = ?, description = ? WHERE product_id = ?";
        String updateDrinkSql = "UPDATE drink SET drink_id = ? , drink_size_id = ? , invoice_id = ? , product_id = ? , flavor = ? " +
                "WHERE product_id = ?";
        try {
            int numProductRowsAffected = db.queryForObject(updateProductSql, int.class) ;
            int numDrinkRowsAffected = db.queryForObject(updateDrinkSql, int.class);
            if(numDrinkRowsAffected == 0 || numProductRowsAffected == 0){
                throw new DaoException("No products found. Check Product IDs");
            }
        }catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getDrinkByDrinkId(drink.getDrinkId());

    }

    @Override
    public void deleteDrinkById(int id) {
        String sql = "DELETE FROM drink WHERE drink_id  = ?";
        try {
            db.update(sql, id);
        } catch (DataIntegrityViolationException eie) {
            System.out.println("An error happened getting the drink by ID");
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public Drink mapRowSet(SqlRowSet rowSet) {
        Drink mappedDrink = new Drink(
                rowSet.getInt("p.product_id"),
                rowSet.getBigDecimal("p.price"),
                rowSet.getString("p.description"),
                rowSet.getInt("d.drink_id"),
                rowSet.getInt("d.drink_size_id"),
                rowSet.getInt("d.invoice_id"),
                rowSet.getString("d.flavor")
                );
        return mappedDrink;
    }

}
