package org.example.november_market_2.core.converters;

import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    OrderItemConverter orderItemConverter;
    UserConverter userConverter;

//    public OrderDto entityToDto(Order o) {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(o.getId());
//        orderDto.setItems(o.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
//        orderDto.setUser(userConverter.entityToDto(o.getUser()));
//        orderDto.setTotalPrice(o.getTotalPrice());
//        return orderDto;
//    }

}
