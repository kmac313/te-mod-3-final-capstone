package com.techelevator.model;

import java.math.BigDecimal;
//TODO: Consider extending to a SpecialtyPizza model
public class Pizza extends Product{
    private int pizzaId;
    private int invoiceId;
    private String sizeId;
    private String pizzaName;
    private String crust;
    private String sauce;
    private BigDecimal total;
    private String additionalInstructions;

    public Pizza(int productId, BigDecimal price, String description, int pizzaId, int invoiceId, String sizeId,
                 String pizzaName, String crust, String sauce, BigDecimal total, String additionalInstructions) {
        super(productId, price, description);
        this.pizzaId = pizzaId;
        this.invoiceId = invoiceId;
        this.sizeId = sizeId;
        this.pizzaName = pizzaName;
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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
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
