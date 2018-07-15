package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.exception.ProductQueryException;
import com.shoppingcart.simulator.ProductRepository;
import com.shoppingcart.simulator.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public Product getProduct(String id) {
        if(!validate(id)) {
            throw new ProductQueryException("Empty product id");
        }
        Product product = productRepository.getProduct(id);
        if(null == product) {
            throw new ProductQueryException("Product does not exists");
        }
        return product;
    }

    private boolean validate(String productId) {
        if(null == productId || "".equals(productId.trim())) {
            return false;
        }
        return true;
    }
}
