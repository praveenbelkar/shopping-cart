package com.shoppingcart.fixture;

import com.shoppingcart.domain.ShoppingCart;

import java.util.UUID;

public class ShoppingCartTestDataFixture {
    public String SHOPPING_CART_ID = UUID.randomUUID().toString();

    public static ShoppingCart getDefaultShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        return shoppingCart;
    }
}
