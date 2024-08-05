package com.techelevator.model;

import java.math.BigDecimal;

public class Drink extends Product{
    private int drinkId;
    private int productId;
    private int orderId;
    private String drinkSize;


    public Drink(int drinkId, int orderId, String drinkSize, BigDecimal price,
                 int productId, String description) {
        super(productId, price, description);
        this.drinkId = drinkId;
        this.orderId = orderId;
        this.drinkSize = drinkSize;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }
}
