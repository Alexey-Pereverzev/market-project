package org.example.november_market_2.core.converters;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.OrderDto;
import org.example.november_market_2.core.entities.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(o.getId());
        orderDto.setItems(o.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        orderDto.setTotalPrice(o.getTotalPrice());
        orderDto.setUsername(o.getUsername());
        return orderDto;
    }
}
