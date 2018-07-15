package com.shoppingcart.service;

import static org.junit.Assert.*;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.fixture.ProductTestDataFixture;
import com.shoppingcart.fixture.ShoppingCartTestDataFixture;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ShoppingCartTest {

    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        shoppingCartService = new ShoppingCartServiceImpl();
    }

    @Test
    public void testSetUp() {
        assertNotNull(shoppingCartService);
    }

    @Test
    public void addProduct_given_emptyShoppingCart_when_fiveDoveSoapsAdded_then_shoppingCartContainsFiveDoveSoaps() {
        //Arrange
        ShoppingCart shoppingCart = ShoppingCartTestDataFixture.getDefaultShoppingCart();
        Product doveSoap = ProductTestDataFixture.getSingleDoveSoap();
        Product otherSoap = ProductTestDataFixture.getOtherSoap();

        //Act
        shoppingCart = shoppingCartService.addItem(shoppingCart, doveSoap, 5L);

        //Assert
        assertNotNull(shoppingCart);
        assertNotNull(shoppingCart.getItems());
        assertThat(shoppingCart.getItems().size(), Matchers.is(1));
        assertTrue(shoppingCart.getItems().containsKey(doveSoap));
        assertThat(shoppingCart.getItems().get(doveSoap), Matchers.is(5L));
        BigDecimal expectedValue = new BigDecimal(199.95);
        expectedValue = expectedValue.setScale(2, BigDecimal.ROUND_HALF_UP);
        assertThat(shoppingCart.getTotalPrice(), Matchers.is(expectedValue));

        assertFalse(shoppingCart.getItems().containsKey(otherSoap));
    }


}
