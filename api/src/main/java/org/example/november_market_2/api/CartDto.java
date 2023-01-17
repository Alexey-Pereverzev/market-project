package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;


@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Список элементов корзины", required = true)
    private List<LineItemDto> items;

    @Schema(description = "Сумма заказа корзины", required = true, example = "12900.00")
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
