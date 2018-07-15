package com.shoppingcart.simulator;

import com.shoppingcart.domain.ShoppingCart;

public interface ShoppingCartRepository {

    public ShoppingCart getShoppingCart(String sessionId);

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
}
