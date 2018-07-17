package com.shoppingcart.service;

import com.shoppingcart.domain.ItemDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.exception.AddItemException;
import com.shoppingcart.exception.ProductQueryException;
import com.shoppingcart.simulator.ShoppingCartRepository;
import com.shoppingcart.simulator.ShoppingCartRepositoryImpl;

import java.util.Map;
import java.util.UUID;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductService productService;
    private ShoppingCartRepository shoppingCartRepository;
    private PricingService pricingService;

    public ShoppingCartServiceImpl() {
        this.productService = new ProductServiceImpl();
        this.shoppingCartRepository = new ShoppingCartRepositoryImpl();
        this.pricingService = new PricingServiceImpl();
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
        Long existingQuantityOfProductsInShoppingCart = getExistingQuantityOfProductInShoppingCart(product, shoppingCart);
        quantity = quantity + existingQuantityOfProductsInShoppingCart;
        ItemDetail itemDetail = createItemDetail(product, quantity);
        shoppingCart.getItems().put(product, itemDetail);
        shoppingCart.setTotalPrice(pricingService.calculateShoppingCartTotalPrice(shoppingCart));
        shoppingCart.setTotalTax(pricingService.calculateShoppingCartTotalTax(shoppingCart));
        return shoppingCart;
    }

    private Long getExistingQuantityOfProductInShoppingCart(Product product, ShoppingCart shoppingCart) {
        Map<Product, ItemDetail> items = shoppingCart.getItems();
        Long existingQuantityOfProductInShoppingCart = 0L;
        if(items == null || items.size() == 0 || null == items.get(product)) {
            existingQuantityOfProductInShoppingCart = 0L;
        } else {
            existingQuantityOfProductInShoppingCart = items.get(product).getQuantity();
            if(existingQuantityOfProductInShoppingCart == null) {
                existingQuantityOfProductInShoppingCart = 0L;
            }
        }
        return existingQuantityOfProductInShoppingCart;
    }

    private ItemDetail createItemDetail(Product product, Long quantity) {
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setProduct(product);
        itemDetail.setQuantity(quantity);
        itemDetail.setTax(pricingService.calculateTaxForItem(itemDetail));
        itemDetail.setTotalPrice(pricingService.calculateTotalPriceForItem(itemDetail));
        return itemDetail;
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

}
