package org.example.november_market_2.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Запрос на получение id для гостевой корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }


    @Operation(
            summary = "Запрос на получение корзины по заголовку username или гостевому id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }


    @Operation(
            summary = "Запрос на слияние гостевой корзины и корзины пользователя",
            responses = {
                    @ApiResponse(
                            description = "Корзины успешно соединены", responseCode = "200"
                    )
            }
    )
    @GetMapping("/merge/{guestCartId}")
    public void mergeCarts(@RequestHeader String username, @PathVariable String guestCartId) {
        cartService.mergeCarts(username, guestCartId);
    }

    @Operation(
            summary = "Запрос на добавление продукта в корзину",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно добавлен", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId,
                                 @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @Operation(
            summary = "Запрос на удаление 1 единицы продукта из корзины",
            responses = {
                    @ApiResponse(
                            description = "Количество продукта в корзине уменьшено на 1", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/decrease/{productId}")
    public void decrease(@RequestHeader(required = false) String username, @PathVariable String guestCartId,
                         @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.decrease(currentCartId, productId);
    }

    @Operation(
            summary = "Запрос на очистку корзины",
            responses = {
                    @ApiResponse(
                            description = "Корзина успешно очищена", responseCode = "200"
                    )
            }
    )
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
