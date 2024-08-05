package com.techelevator.model;

import java.math.BigDecimal;

public class Invoice {
    private int invoiceId;
    private BigDecimal total;
    private boolean isDelivery;
    private int customerId;

    public Invoice(int invoiceId, int customerId, BigDecimal total, Boolean isDelivery ) {
        this.invoiceId = invoiceId;
        this.total = total;
        this.isDelivery = isDelivery;
        this.customerId = customerId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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
