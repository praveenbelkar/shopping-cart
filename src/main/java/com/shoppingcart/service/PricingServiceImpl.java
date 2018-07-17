package com.shoppingcart.service;

import com.shoppingcart.domain.ItemDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PricingServiceImpl implements PricingService {

    private static final BigDecimal TAX_RATE = new BigDecimal(12.5);

    @Override
    public BigDecimal calculateTaxForItem(ItemDetail itemDetail) {
        BigDecimal taxForProduct = itemDetail.getProduct().getPrice().multiply(new BigDecimal(itemDetail.getQuantity())).multiply(TAX_RATE).divide(new BigDecimal(100));
        taxForProduct = taxForProduct.setScale(2, BigDecimal.ROUND_HALF_UP);
        return taxForProduct;
    }

    @Override
    public BigDecimal calculateShoppingCartTotalPrice(ShoppingCart shoppingCart) {
        /*List<BigDecimal> totalSum = shoppingCart.getItems().values().stream().map(itemDetail -> {
            return itemDetail.getTotalPrice();
        }).collect(Collectors.toList());*/
        return shoppingCart.getItems().values().stream().map(mapToTotalPrice()).reduce(new BigDecimal(0), addAllValues());
    }

    private BinaryOperator<BigDecimal> addAllValues() {
        return (value1, value2)->{
          return value1.add(value2);
        };
    }

    private Function<ItemDetail, BigDecimal> mapToTotalPrice() {
        return itemDetail->itemDetail.getTotalPrice();
    }

    @Override
    public BigDecimal calculateTotalPriceForItem(ItemDetail itemDetail) {
        BigDecimal totalPrice  = itemDetail.getProduct().getPrice().multiply(new BigDecimal(itemDetail.getQuantity()));
        totalPrice = totalPrice.add(itemDetail.getTax());
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        return totalPrice;
    }

    @Override
    public BigDecimal calculateShoppingCartTotalTax(ShoppingCart shoppingCart) {
        /*List<BigDecimal> totalSum = shoppingCart.getItems().values().stream().map(itemDetail -> {
            return itemDetail.getTotalPrice();
        }).collect(Collectors.toList());*/
        return shoppingCart.getItems().values().stream().map(itemDetail -> itemDetail.getTax()).reduce(new BigDecimal(0), addAllValues());
    }

}
