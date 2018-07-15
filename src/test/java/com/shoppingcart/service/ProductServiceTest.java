package com.shoppingcart.service;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import com.shoppingcart.domain.Product;
import com.shoppingcart.exception.ProductQueryException;
import com.shoppingcart.fixture.ProductTestDataFixture;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

public class ProductServiceTest {

    private ProductService productService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    public void getProduct_given_productExists_when_queriedWithExistingProductId_then_returnProduct() {
        //Arrange
        Product expectedDoveSoap = ProductTestDataFixture.getSingleDoveSoap();
        String productId = expectedDoveSoap.getId();

        //Act
        Product doveSoap = productService.getProduct(productId);

        //Assert
        assertThat(doveSoap, notNullValue());
        assertThat(doveSoap, is(expectedDoveSoap));
        assertThat(doveSoap.getId(), is(expectedDoveSoap.getId()));
        assertThat(doveSoap.getName(), is(expectedDoveSoap.getName()));
        assertThat(doveSoap.getPrice(), is(expectedDoveSoap.getPrice()));
    }

    @Test
    public void getProduct_when_productDoesNotExists_when_queriedWithNonExistingProductId_then_throwException() {
        //Arrange
        String nonExistingProductId = UUID.randomUUID().toString();

        //Expect
        thrown.expect(ProductQueryException.class);
        thrown.expectMessage("Product does not exists");

        //Assert
        productService.getProduct(nonExistingProductId);
    }

    @Test
    public void getProduct_when_productIdIsNull_then_throwException() {
        //Expect
        thrown.expect(ProductQueryException.class);
        thrown.expectMessage("Empty product id");

        //Assert
        productService.getProduct(null);

    }

    @Test
    public void getProduct_when_productIdIsEmpty_then_throwException() {
        //Expect
        thrown.expect(ProductQueryException.class);
        thrown.expectMessage("Empty product id");

        //Assert
        productService.getProduct(" ");

    }
}
