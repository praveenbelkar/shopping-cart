package com.shoppingcart.service;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.InvalidAddItemException;
import com.shoppingcart.fixture.ProductTestDataFixture;
import com.shoppingcart.fixture.ShoppingCartTestDataFixture;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ShoppingCartTest {

    private ShoppingCartService shoppingCartService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        shoppingCartService = new ShoppingCartServiceImpl();
    }

    @Test
    public void testSetUp() {
        assertNotNull(shoppingCartService);
    }

    @Test
    public void addItem_given_emptyShoppingCart_when_fiveDoveSoapsAdded_then_shoppingCartContainsFiveDoveSoaps() {
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
        assertThat(shoppingCart.getTotalPrice(), is(expectedValue));

        assertFalse(shoppingCart.getItems().containsKey(otherSoap));
    }

    @Test
    public void addItem_given_emptyShoppingCart_when_nothingAdded_then_throwException() {
        //Arrange
        ShoppingCart shoppingCart = ShoppingCartTestDataFixture.getDefaultShoppingCart();

        //Assert
        thrown.expect(InvalidAddItemException.class);
        thrown.expectMessage("No product is selected");

        //Act
        shoppingCart = shoppingCartService.addItem(shoppingCart, null, 0L);

    }

}
