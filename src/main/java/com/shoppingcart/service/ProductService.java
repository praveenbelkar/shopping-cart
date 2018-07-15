package com.shoppingcart.service;

import com.shoppingcart.domain.Product;

public interface ProductService {

    /**
     * Gets the Product details for the given product id
     * @param id - the product id for which details have to be fetched
     * @return - the Product object representing the given id
     */
    public Product getProduct(String id);
}
