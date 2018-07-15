package com.shoppingcart.simulator;

import com.shoppingcart.domain.Product;

public interface ProductRepository {
    public Product getProduct(String productId);
}
