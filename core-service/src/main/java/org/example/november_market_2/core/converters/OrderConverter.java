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
        return OrderDto.Builder.newBuilder()
                .withId(o.getId())
                .withItems(o.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()))
                .withTotalPrice(o.getTotalPrice())
                .withUsername(o.getUsername())
                .withAddress(o.getAddress())
                .withPhoneNumber(o.getPhoneNumber())
                .build();
    }
}
