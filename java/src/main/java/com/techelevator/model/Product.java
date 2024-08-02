package com.techelevator.model;

import java.math.BigDecimal;

public abstract class Product {
    private int productId;
    private BigDecimal price;
    private String description;

    public Product(int productId, BigDecimal price, String description) {
        this.productId = productId;
        this.price = price;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
