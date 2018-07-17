package com.shoppingcart.service;

import com.shoppingcart.domain.ItemDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;

import java.math.BigDecimal;

public interface PricingService {
    public BigDecimal calculateTaxForItem(ItemDetail itemDetail);
    public BigDecimal calculateShoppingCartTotalPrice(ShoppingCart shoppingCart);
    public BigDecimal calculateTotalPriceForItem(ItemDetail itemDetail);
    public BigDecimal calculateShoppingCartTotalTax(ShoppingCart shoppingCart);
}
