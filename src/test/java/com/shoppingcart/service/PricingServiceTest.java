package com.shoppingcart.service;

import com.shoppingcart.domain.ItemDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.fixture.ProductTestDataFixture;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.math.BigDecimal;

public class PricingServiceTest {

    private PricingService pricingService;

    @Before
    public void setUp(){
        pricingService = new PricingServiceImpl();
    }

    @Test
    public void calculateTaxForProduct_given_productWithPriceAndTaxRateInPercentageAndQuantityOfProduct_then_returnTotalTaxOnThatProduct() {
        //Arrange
        ItemDetail doveItemDetail = ProductTestDataFixture.getDoveItemDetail();
        doveItemDetail.setQuantity(2L);

        //Act
        BigDecimal tax = pricingService.calculateTaxForItem(doveItemDetail);

        //Assert
        BigDecimal expectedTax = new BigDecimal(10.00);
        expectedTax = expectedTax.setScale(2, BigDecimal.ROUND_HALF_UP);
        assertThat(tax, is(expectedTax));
    }
}
