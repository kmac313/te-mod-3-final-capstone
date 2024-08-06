package com.techelevator.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Invoice {
    private int invoiceId;
    private BigDecimal total;
    private boolean isDelivery;
    private int customerId;
    private boolean isComplete;
    private Timestamp timestamp;

    public Invoice(int invoiceId, int customerId, BigDecimal total, boolean isDelivery,
                   boolean isComplete, Timestamp timestamp) {
        this.invoiceId = invoiceId;
        this.total = total;
        this.isDelivery = isDelivery;
        this.customerId = customerId;
        this.isComplete = isComplete;
        this.timestamp = timestamp;
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
