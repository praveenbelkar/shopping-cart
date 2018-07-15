package com.shoppingcart.simulator;

import com.shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {

    public static final String DOVE_ID = UUID.randomUUID().toString();
    public static final String DOVE_SOAP_NAME = "Dove";
    public static final BigDecimal DOVE_PRICE = new BigDecimal(39.99);

    public static Map<String, Product> products = new HashMap<>();
    static {
        Product doveSoap = getSingleDoveSoap();
        products.put(doveSoap.getId(), doveSoap);
    }


    public static Product getSingleDoveSoap() {
        Product doveSoap = new Product();
        doveSoap.setId(DOVE_ID);
        doveSoap.setName(DOVE_SOAP_NAME);
        doveSoap.setPrice(DOVE_PRICE);
        return doveSoap;
    }

    @Override
    public Product getProduct(String productId) {
        //if(products.containsKey(productId)) {
            return products.get(productId);
        /*}
        return null;*/
    }
}
