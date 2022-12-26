package org.example.november_market_2.core.converters;

import org.example.november_market_2.api.OrderItemDto;
import org.example.november_market_2.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getOrder().getId(), orderItem.getProduct().getId(),
                orderItem.getProduct().getTitle(), orderItem.getPricePerProduct(), orderItem.getPrice(),
                orderItem.getQuantity());
    }
}
