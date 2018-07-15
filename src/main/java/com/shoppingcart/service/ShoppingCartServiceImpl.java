package com.shoppingcart.service;

import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.AddItemException;
import com.shoppingcart.exception.ProductQueryException;
import com.shoppingcart.simulator.ShoppingCartRepository;
import com.shoppingcart.simulator.ShoppingCartRepositoryImpl;

import java.math.BigDecimal;
import java.util.UUID;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductService productService;
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl() {
        this.productService = new ProductServiceImpl();
        this.shoppingCartRepository = new ShoppingCartRepositoryImpl();
    }

    @Override
    public ShoppingCart addItem(String sessionId, String productId, Long quantity) {
        if(!validate(productId, quantity)) {
            throw new AddItemException("No product is selected");
        }
        Product product = null;
        try{
            product = productService.getProduct(productId);
        } catch (ProductQueryException pqe) {
            throw new AddItemException("Selected product does not exists");
        }

        ShoppingCart shoppingCart = fetchExistingShoppingCartOrCreateNew(sessionId);
        Long existingQuantityOfProductsInShoppingCart = shoppingCart.getItems().get(product);
        if(existingQuantityOfProductsInShoppingCart == null) {
            existingQuantityOfProductsInShoppingCart = 0L;
        }
        quantity = quantity + existingQuantityOfProductsInShoppingCart;

        shoppingCart.getItems().put(product, quantity);
        shoppingCart.setTotalPrice(calculateTotalPrice(product, quantity));

        return shoppingCart;
    }

    private ShoppingCart fetchExistingShoppingCartOrCreateNew(String sessionId) {
        if(null == sessionId || "".equals(sessionId.trim())) {
            return createNewShoppingCart();
        }
        ShoppingCart existingShoppingCart = shoppingCartRepository.getShoppingCart(sessionId);
        if(null == existingShoppingCart) {
            existingShoppingCart = createNewShoppingCart();
        }
        return existingShoppingCart;
    }

    private ShoppingCart createNewShoppingCart() {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setSessionId(UUID.randomUUID().toString());
        shoppingCartRepository.createShoppingCart(newShoppingCart);
        return newShoppingCart;
    }

    private boolean validate(String productId, Long quantity) {
        if(null == productId || "".equals(productId.trim()) || quantity <= 0) {
            return false;
        }
        return true;
    }

    private BigDecimal calculateTotalPrice(Product product, Long quantity) {
        BigDecimal totalPrice  = product.getPrice().multiply(new BigDecimal(quantity));
        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        return totalPrice;
    }


}
