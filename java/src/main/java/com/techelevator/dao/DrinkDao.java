package com.techelevator.dao;

import com.techelevator.model.Drink;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface DrinkDao {
    List<Drink> getDrinks();
    Drink getDrinkByDrinkId(int id);
    Drink createDrink(Drink drink);
    Drink updateDrink(Drink drink);
    void deleteDrinkById(int id);
    public Drink mapRowSet(SqlRowSet rowSet);
}
