package org.example.november_market_2.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.CartDto;
import org.example.november_market_2.api.StringResponse;
import org.example.november_market_2.cart.converters.CartConverter;
import org.example.november_market_2.cart.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }

    @GetMapping("/merge/{guestCartId}")
    public void mergeCarts(@RequestHeader String username, @PathVariable String guestCartId) {
        cartService.mergeCarts(username, guestCartId);
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId,
                                 @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/decrease/{productId}")
    public void decrease(@RequestHeader(required = false) String username, @PathVariable String guestCartId,
                         @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.decrease(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }
}
