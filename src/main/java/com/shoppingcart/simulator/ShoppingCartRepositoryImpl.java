package com.shoppingcart.simulator;

import com.shoppingcart.domain.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    public static Map<String, ShoppingCart> shoppingCarts = new HashMap<>();

    @Override
    public ShoppingCart getShoppingCart(String sessionId) {
        return shoppingCarts.get(sessionId);
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.put(shoppingCart.getSessionId(), shoppingCart);
        return shoppingCart;
    }
}
