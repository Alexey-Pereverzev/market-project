package org.example.november_market_2.cart.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.cart.integrations.ProductServiceIntegration;
import org.example.november_market_2.cart.utils.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor            //  позволяет не инжектить корзину, это важно, потому что у нас нет такого бина
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void clearCart() {
        cart.clear();
    }

    public void decrease(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.decrease(p);
    }
}
