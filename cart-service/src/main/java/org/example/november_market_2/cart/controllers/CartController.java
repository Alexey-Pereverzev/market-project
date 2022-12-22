package org.example.november_market_2.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.CartDto;
import org.example.november_market_2.cart.converters.CartConverter;
import org.example.november_market_2.cart.services.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/decrease/{productId}")
    public void decrease(@PathVariable Long productId) {
        cartService.decrease(productId);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
