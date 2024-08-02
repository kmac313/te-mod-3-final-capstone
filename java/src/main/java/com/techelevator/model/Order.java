package com.techelevator.model;

import java.math.BigDecimal;

public class Order {
    private int order_id;
    private BigDecimal total;
    private boolean isDelivery;
    private int customerId;

    public Order(int order_id, BigDecimal total, boolean isDelivery, int customerId) {
        this.order_id = order_id;
        this.total = total;
        this.isDelivery = isDelivery;
        this.customerId = customerId;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
