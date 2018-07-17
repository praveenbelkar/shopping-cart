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

    public static final String AXE_DEO_ID = UUID.randomUUID().toString();
    public static final String AXE_DEO_NAME = "Axe Deo";
    public static final BigDecimal AXE_DEO_PRICE = new BigDecimal(99.99);

    public static Map<String, Product> products = new HashMap<>();
    static {
        Product doveSoap = createProduct(DOVE_ID, DOVE_SOAP_NAME, DOVE_PRICE);
        products.put(doveSoap.getId(), doveSoap);

        Product axeDeo = createProduct(AXE_DEO_ID, AXE_DEO_NAME, AXE_DEO_PRICE);
        products.put(axeDeo.getId(), axeDeo);
    }

    public static Product createProduct(String id, String name, BigDecimal price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        //if(products.containsKey(productId)) {
            return products.get(productId);
        /*}
        return null;*/
    }
}
