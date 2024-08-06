package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;


public class Pizza extends Product{
    private int pizzaId;
    private int invoiceId;
    private String pizzaName;
    private BigDecimal total;
    private String additionalInstructions;
    private List<Product> components;

    public Pizza(int productId, int productCategoryId,String productCategoryDescription,  BigDecimal price,
                 String description, int pizzaId, int invoiceId,String pizzaName,BigDecimal total,
                 String additionalInstructions, int quantity) {
        super(productId,productCategoryId, productCategoryDescription, price, description, quantity);
        this.pizzaId = pizzaId;
        this.invoiceId = invoiceId;
        this.pizzaName = pizzaName;
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

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
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

    public List<Product> getComponents() {
        return components;
    }

    public void setComponents(List<Product> components) {
        this.components = components;
    }
    public void addComponent(Product component){
        this.components.add(component);
    }

    //TODO how to make sure that component passed in is at the corretion memory address?
    public void removeComponent(Product component){
        this.components.remove(component);
    }
}
