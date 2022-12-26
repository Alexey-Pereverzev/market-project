package org.example.november_market_2.core.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.OrderDto;
import org.example.november_market_2.core.converters.OrderItemConverter;
import org.example.november_market_2.core.converters.UserConverter;
import org.example.november_market_2.core.entities.Order;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.example.november_market_2.core.integrations.CartServiceIntegration;
import org.example.november_market_2.core.services.OrderService;
import org.example.november_market_2.core.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartServiceIntegration cartServiceIntegration;
    private final UserService userService;
    private final UserConverter userConverter;

    private final OrderItemConverter orderItemConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal) {
        orderService.createNewOrder(cartServiceIntegration.getCurrentCart().getItems(),
                userConverter.entityToDto(userService.findByUsername(principal.getName()).
                        orElseThrow(() -> new ResourceNotFoundException("Пользователь с именем " + principal.getName()
                                + " не найден"))));
    }

    @GetMapping("/{id}")
    public Optional<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = new OrderDto();
        Optional<Order> o = orderService.findById(id);
        if (o.isEmpty()) {
            return Optional.empty();
        } else {
            orderDto.setUser(userConverter.entityToDto(o.get().getUser()));
            orderDto.setItems(orderService.findItemsByOrderId(id).stream().map(orderItemConverter::entityToDto)
                    .collect(Collectors.toList()));
            orderDto.setId(id);
            orderDto.setTotalPrice(o.get().getTotalPrice());
            return Optional.of(orderDto);
        }
    }
}

