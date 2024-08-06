package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.Pizza;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface PizzaDao {
    List<Pizza> getPizzas();
    Pizza getPizzaById(int id);
    Pizza createPizza(Pizza pizza);
    Pizza updatePizza(Pizza pizza);
    void deletePizzaById(int id);
    Pizza updatePizzaComponents(Pizza pizza);
    public Pizza mapRowSet(SqlRowSet rowSet);
}
