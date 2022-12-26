package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.api.UserDto;
import org.example.november_market_2.core.entities.Order;
import org.example.november_market_2.core.entities.OrderItem;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.example.november_market_2.core.repositories.OrderItemRepository;
import org.example.november_market_2.core.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderItem> findItemsByOrderId(Long id) {
        return orderItemRepository.findByOrderId(id);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void createNewOrder(List<LineItemDto> lineItemDtos, UserDto userDto) {
        Order order = new Order();
        order.setTotalPrice(BigDecimal.ZERO);
        order.setUser(userService.findByUsername(userDto.getUsername()).orElseThrow(() ->
                new ResourceNotFoundException("Пользователь с именем: " + userDto.getUsername() + " не найден")));
        for (LineItemDto lineItemDto : lineItemDtos) {
            OrderItem item = orderItemService.createFromLineItem(lineItemDto);
            item.setOrder(order);
            order.getItems().add(item);
            order.setTotalPrice(order.getTotalPrice().add(item.getPrice()));
        }
        orderRepository.save(order);
    }





}
