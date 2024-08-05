package com.techelevator.model;

import java.math.BigDecimal;

public class Drink extends Product{
    private int drinkId;
    private int drinkSizeId;
    private int invoiceId;
    private String flavor;

    public Drink(int productId, BigDecimal price, String description, int drinkId,
                 int drinkSizeId, int invoiceId, String flavor) {
        super(productId, price, description);
        this.drinkId = drinkId;
        this.drinkSizeId = drinkSizeId;
        this.invoiceId = invoiceId;
        this.flavor = flavor;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getDrinkSizeId() {
        return drinkSizeId;
    }

    public void setDrinkSizeId(int drinkSizeId) {
        this.drinkSizeId = drinkSizeId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

}
