package com.techelevator.model;

import java.math.BigDecimal;

public class Pizza extends Product{
    private int pizzaId;
    private int orderId;
    private String sizeId;
    private String crust;
    private String sauce;
    private BigDecimal total;
    private String additionalInstructions;

    public Pizza(int pizzaId, int orderId, String sizeId, String crust,
                 String sauce, BigDecimal total, String additionalInstructions,
                 int productId, BigDecimal price, String description) {
        super(productId, price, description);
        this.pizzaId = pizzaId;
        this.orderId = orderId;
        this.sizeId = sizeId;
        this.crust = crust;
        this.sauce = sauce;
        this.total = total;
        this.additionalInstructions = additionalInstructions;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
    }
}
