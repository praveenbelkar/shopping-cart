package com.shoppingcart.fixture;

import com.shoppingcart.domain.ItemDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.simulator.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTestDataFixture {

    public static final String DOVE_ID = ProductRepositoryImpl.DOVE_ID;
    public static final String DOVE_SOAP_NAME = "Dove";
    public static final BigDecimal DOVE_PRICE = new BigDecimal(39.99);

    public static final String OTHER_SOAP_ID = UUID.randomUUID().toString();
    public static final String OTHER_SOAP_NAME = "Other Soap";
    public static final BigDecimal OTHER_SOAP_PRICE = new BigDecimal((20.00));

    public static final String AXE_DEO_ID = ProductRepositoryImpl.AXE_DEO_ID;
    public static final String AXE_DEO_NAME = "Axe Deo";
    public static final BigDecimal AXE_DEO_PRICE = new BigDecimal(99.99);

    public static Product getSingleDoveSoap() {
        Product doveSoap = new Product();
        doveSoap.setId(DOVE_ID);
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

    public static Product getAxeDeo() {
        Product axeDeo = new Product();
        axeDeo.setId(AXE_DEO_ID);
        axeDeo.setName(AXE_DEO_NAME);
        axeDeo.setPrice(AXE_DEO_PRICE);
        return axeDeo;
    }

    public static ItemDetail getDoveItemDetail() {
        ItemDetail doveItemDetail = new ItemDetail();
        doveItemDetail.setProduct(getSingleDoveSoap());
        return doveItemDetail;
    }
}
