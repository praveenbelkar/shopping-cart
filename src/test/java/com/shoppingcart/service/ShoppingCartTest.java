package com.shoppingcart.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.AddItemException;
import com.shoppingcart.fixture.ProductTestDataFixture;
import com.shoppingcart.fixture.ShoppingCartTestDataFixture;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.UUID;

public class ShoppingCartTest {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        productService = new ProductServiceImpl();
        shoppingCartService = new ShoppingCartServiceImpl(productService);
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
        shoppingCart = shoppingCartService.addItem(shoppingCart.getSessionId(), doveSoap.getId(), 5L);

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

        //Expect
        thrown.expect(AddItemException.class);
        thrown.expectMessage("No product is selected");

        //Act
        shoppingCart = shoppingCartService.addItem(shoppingCart.getSessionId(), null, 0L);
    }

    @Test
    public void addItem_given_emptyShoppingCart_when_invalidProductAdded_then_throwException() {
        //Arrange
        ShoppingCart shoppingCart = ShoppingCartTestDataFixture.getDefaultShoppingCart();
        String invalidProductId = UUID.randomUUID().toString();

        //Expect
        thrown.expect(AddItemException.class);
        thrown.expectMessage("Selected product does not exists");

        //Act
        shoppingCart = shoppingCartService.addItem(shoppingCart.getSessionId(), invalidProductId, 2L);

    }

}
