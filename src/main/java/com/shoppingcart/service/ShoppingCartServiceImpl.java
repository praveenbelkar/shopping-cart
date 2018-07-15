package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.AddItemException;
import com.shoppingcart.exception.ProductQueryException;

import java.math.BigDecimal;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductService productService;

    public ShoppingCartServiceImpl(ProductService productService) {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public ShoppingCart addItem(ShoppingCart shoppingCart, String productId, Long quantity) {
        if(!validate(productId, quantity)) {
            throw new AddItemException("No product is selected");
        }
        Product product = null;
        try{
            product = productService.getProduct(productId);
        } catch (ProductQueryException pqe) {
            throw new AddItemException("Selected product does not exists");
        }

        shoppingCart.getItems().put(product, quantity);
        BigDecimal totalPrice  = product.getPrice().multiply(new BigDecimal(quantity));
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        shoppingCart.setTotalPrice(totalPrice);

        return shoppingCart;
    }

    private boolean validate(String productId, Long quantity) {
        if(null == productId || "".equals(productId.trim()) || quantity <= 0) {
            return false;
        }
        return true;
    }
}
