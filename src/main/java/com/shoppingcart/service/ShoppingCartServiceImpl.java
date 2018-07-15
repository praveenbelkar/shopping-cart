package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.InvalidAddItemException;

import java.math.BigDecimal;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Override
    public ShoppingCart addItem(ShoppingCart shoppingCart, Product product, Long quantity) {
        if(!validate(product, quantity)) {
            throw new InvalidAddItemException("No product is selected");
        }
        shoppingCart.getItems().put(product, quantity);
        BigDecimal totalPrice  = product.getPrice().multiply(new BigDecimal(quantity));
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        shoppingCart.setTotalPrice(totalPrice);

        return shoppingCart;
    }

    private boolean validate(Product product, Long quantity) {
        if(null == product || quantity <= 0) {
            return false;
        }
        return true;
    }
}
