package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;

public interface ShoppingCartService {

    public ShoppingCart addItem(ShoppingCart shoppingCart, Product product, Long quantity);
}
