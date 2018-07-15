package com.shoppingcart.fixture;

import com.shoppingcart.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTestDataFixture {

    public static final String DOVE_ID_1 = UUID.randomUUID().toString();
    public static final String DOVE_SOAP_NAME = "Dove";
    public static final BigDecimal DOVE_PRICE = new BigDecimal(39.99);

    public static final String OTHER_SOAP_ID = UUID.randomUUID().toString();
    public static final String OTHER_SOAP_NAME = "Other Soap";
    public static final BigDecimal OTHER_SOAP_PRICE = new BigDecimal((20.00));

    public static Product getSingleDoveSoap() {
        Product doveSoap = new Product();
        doveSoap.setId(DOVE_ID_1);
        doveSoap.setName(DOVE_SOAP_NAME);
        doveSoap.setPrice(DOVE_PRICE);
        return doveSoap;
    }

    public static Product getOtherSoap() {
        Product otherSoap = new Product();
        otherSoap.setId(OTHER_SOAP_ID);
        otherSoap.setName(OTHER_SOAP_NAME);
        otherSoap.setPrice(OTHER_SOAP_PRICE);
        return otherSoap;
    }
}
