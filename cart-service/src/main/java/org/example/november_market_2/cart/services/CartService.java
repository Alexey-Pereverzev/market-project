package org.example.november_market_2.cart.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.cart.integrations.ProductServiceIntegration;
import org.example.november_market_2.cart.utils.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor            //  позволяет не инжектить корзину, это важно, потому что у нас нет такого бина
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }


    public Cart getCurrentCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            Cart cart = new Cart();
            carts.put(cartId, cart);
        }
        return carts.get(cartId);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        getCurrentCart(cartId).add(p);
    }


    public void clearCart(String cartId) {
        getCurrentCart(cartId).clear();
    }

    public void decrease(String cartId, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        getCurrentCart(cartId).decrease(p);
    }

    public void mergeCarts(String username, String guestCartId) {
        if (carts.containsKey(guestCartId)) {
            if (!carts.containsKey(username)) {
                Cart newCart = new Cart();
                newCart.setTotalPrice(carts.get(guestCartId).getTotalPrice());
                for (int i = 0; i < carts.get(guestCartId).getItems().size(); i++) {
                    for (int j = 0; j < carts.get(guestCartId).getItems().get(i).getQuantity(); j++) {
                        newCart.add(productServiceIntegration.findById(carts.get(guestCartId).getItems().get(i).getProductId()));
                    }
                }
//                newCart.setItems(carts.get(guestCartId).getItems());
                carts.put(username, newCart);
                clearCart(guestCartId);
            } else {
                carts.get(username).setTotalPrice(carts.get(username).getTotalPrice().add(carts.get(guestCartId).getTotalPrice()));
                for (int i = 0; i < carts.get(guestCartId).getItems().size(); i++) {
                    for (int j = 0; j < carts.get(guestCartId).getItems().get(i).getQuantity(); j++) {
                        addToCart(username, carts.get(guestCartId).getItems().get(i).getProductId());
                    }
                }
                clearCart(guestCartId);
            }
        }
    }
}
