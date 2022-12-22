package org.example.november_market_2.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;

    private UserDto user;

    private BigDecimal totalPrice;

    private List<OrderItemDto> items;

    public OrderDto() {
    }

    public OrderDto(Long id, UserDto user, BigDecimal totalPrice, List<OrderItemDto> items) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}
