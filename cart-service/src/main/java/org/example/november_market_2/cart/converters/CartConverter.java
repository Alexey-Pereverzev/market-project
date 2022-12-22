package org.example.november_market_2.cart.converters;


import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.CartDto;
import org.example.november_market_2.cart.utils.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final LineItemConverter lineItemConverter;

    public CartDto entityToDto(Cart c) {
        return new CartDto(c.getItems().stream().map(lineItemConverter::entityToDto).collect(Collectors.toList()),
                c.getTotalPrice());
    }
}
