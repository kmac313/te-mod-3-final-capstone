package com.techelevator.model;

import java.math.BigDecimal;

public class Topping {
    private String toppingName;
    private BigDecimal additionalPrice;

    public Topping(String toppingName, BigDecimal additionalPrice) {
        this.toppingName = toppingName;
        this.additionalPrice = additionalPrice;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
}
