package org.example.november_market_2.api;

import java.math.BigDecimal;
import java.util.List;


public class CartDto {
    private List<LineItemDto> items;
    private BigDecimal totalPrice;

    public List<LineItemDto> getItems() {
        return items;
    }

    public void setItems(List<LineItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public CartDto(List<LineItemDto> items, BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
