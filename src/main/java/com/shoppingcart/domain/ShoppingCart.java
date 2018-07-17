package com.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart {

    private String sessionId;
    private Map<Product, ItemDetail> items;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;

    public ShoppingCart() {
        sessionId = UUID.randomUUID().toString();
        items = new HashMap<>();
        totalPrice = new BigDecimal(0);
    }

    public Map<Product, ItemDetail> getItems() {
        return items;
    }

    public void setItems(Map<Product, ItemDetail> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }
}
