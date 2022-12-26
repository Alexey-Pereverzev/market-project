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
        OrderItem orderItem = new OrderItem();

        orderItem.setProduct(productService.findById(lineItemDto.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + lineItemDto.getProductId() + " не найден")
        ));
        orderItem.setQuantity(lineItemDto.getQuantity());
        orderItem.setPrice(lineItemDto.getPrice());
        orderItem.setPricePerProduct(lineItemDto.getPricePerProduct());
        return orderItem;
    }
}
