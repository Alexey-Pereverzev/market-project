package org.example.november_market_2.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.OrderDto;
import org.example.november_market_2.api.StringResponse;
import org.example.november_market_2.core.converters.OrderConverter;
import org.example.november_market_2.core.converters.OrderItemConverter;
import org.example.november_market_2.core.entities.Order;
import org.example.november_market_2.core.exceptions.AppError;
import org.example.november_market_2.core.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemConverter orderItemConverter;
    private final OrderConverter orderConverter;


    @Operation(
            summary = "Запрос на получение заказа по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))
                    ),
                    @ApiResponse(
                            description = "Заказ не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = new OrderDto();
        Optional<Order> o = orderService.findById(id);
        if (o.isEmpty()) {
            return new ResponseEntity<>(new AppError("ORDER_NOT_FOUND", "Заказ не найден"),
                    HttpStatus.NOT_FOUND);
        } else {
            orderDto.setUsername(o.get().getUsername());
            orderDto.setItems(orderService.findItemsByOrderId(id).stream().map(orderItemConverter::entityToDto)
                    .collect(Collectors.toList()));
            orderDto.setId(id);
            orderDto.setTotalPrice(o.get().getTotalPrice());
            orderDto.setPhoneNumber(o.get().getPhoneNumber());
            orderDto.setAddress(o.get().getAddress());
            return ResponseEntity.ok(orderDto);
        }
    }

    @Operation(
            summary = "Запрос на получение списка заказов по заголовку username",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
                    ),
                    @ApiResponse(
                            description = "Заказы по username не найдены", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getUserOrders(@RequestHeader String username) {
        List<OrderDto> result = orderService.findUserOrders(username).stream().map(orderConverter::entityToDto).toList();
        if (result.isEmpty()) {
            return new ResponseEntity<>(new AppError("NO_ORDERS_FOR_SUCH_USER",
                    "Заказы пользователя " + username + " не найдены"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(
            summary = "Запрос на создание заказа пользователя с заголовком username",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка ввода данных (пустой заказ, некорректный телефон)", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка создания заказа", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus
    public ResponseEntity<?> createNewOrder(@RequestHeader String username,
                                            @RequestParam("phoneNumber") String phoneNumber,
                                            @RequestParam("address") String address) {
        String response = orderService.createNewOrder(username, phoneNumber, address);
        if (response.equals("")) {
            return new ResponseEntity<>(new StringResponse("Заказ пользователя " + username + " успешно создан"),
                    HttpStatus.CREATED);
        } else if (response.equals("Пустой заказ") || response.equals("Некорректный номер телефона")) {
            return new ResponseEntity<>(new AppError("INCORRECT_DATA", response),
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new AppError("ORDER_CREATION_ERROR", response),
                    HttpStatus.NOT_FOUND);
        }
    }

}

