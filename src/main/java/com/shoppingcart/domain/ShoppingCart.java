package com.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart {

    private String sessionId;
    private Map<Product, Long> items;
    private BigDecimal totalPrice;

    public ShoppingCart() {
        sessionId = UUID.randomUUID().toString();
        items = new HashMap<>();
        totalPrice = new BigDecimal(0);
    }

    public Map<Product, Long> getItems() {
        return items;
    }

    public void setItems(Map<Product, Long> items) {
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
}
