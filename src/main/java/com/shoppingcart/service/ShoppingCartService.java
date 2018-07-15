package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;

public interface ShoppingCartService {

    /**
     * Adds the product to the shopping cart
     *
     * @param sessionId - The session id for the shopping cart to which product needs to be added
     * @param productId - The product id of the product that needs to be added
     * @param quantity - The number of products that needs to be added to cart
     * @return - The shopping cart which is populated with the product
     */
    public ShoppingCart addItem(String sessionId, String productId, Long quantity);
}
