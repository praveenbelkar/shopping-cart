package com.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Long> items;
    private BigDecimal totalPrice;

    public ShoppingCart() {
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
}
