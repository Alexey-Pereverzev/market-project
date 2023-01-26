package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.core.entities.OrderItem;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final ProductService productService;

    public OrderItem createFromLineItem(LineItemDto lineItemDto) {
        OrderItem orderItem = OrderItem.Builder.newBuilder()
                .withProduct(productService.findById(lineItemDto.getProductId()).orElseThrow(
                        () -> new ResourceNotFoundException("Продукт с id: " + lineItemDto.getProductId() + " не найден")))
                .withQuantity(lineItemDto.getQuantity())
                .withPrice(lineItemDto.getPrice())
                .withPricePerProduct(lineItemDto.getPricePerProduct())
                .build();
        return orderItem;
    }
}
