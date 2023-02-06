package org.example.november_market_2.core.converters;

import org.example.november_market_2.api.OrderItemDto;
import org.example.november_market_2.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        return OrderItemDto.Builder.newBuilder()
                .withOrderId(orderItem.getOrder().getId())
                .withProductId(orderItem.getProduct().getId())
                .withProductTitle(orderItem.getProduct().getTitle())
                .withPricePerProduct(orderItem.getPricePerProduct())
                .withPrice(orderItem.getPrice())
                .withQuantity(orderItem.getQuantity())
                .build();
    }
}
