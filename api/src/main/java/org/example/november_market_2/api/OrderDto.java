package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель заказа")
public class OrderDto {

    @Schema(description = "ID заказа", required = true, example = "14")
    private Long id;

    @Schema(description = "Имя пользователя", required = true, maxLength = 36, minLength = 2, example = "bob")
    private String username;

    @Schema(description = "Сумма заказа", required = true, example = "12900.00")
    private BigDecimal totalPrice;

    @Schema(description = "Список элементов заказа", required = true)
    private List<OrderItemDto> items;

    @Schema(description = "Номер телефона: допускаются цифры 7-13 шт и спецсимволы +,-,),(", required = true,
            example = "+7(923)431-50-12")
    private String phoneNumber;

    @Schema(description = "Адрес доставки", required = true, example = "Санкт-Петербург, Невский пр., д.1")
    private String address;

    public OrderDto() {
    }

    public OrderDto(Long id, String username, BigDecimal totalPrice, List<OrderItemDto> items, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.items = items;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    private OrderDto(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setTotalPrice(builder.totalPrice);
        setItems(builder.items);
        setPhoneNumber(builder.phoneNumber);
        setAddress(builder.address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static final class Builder {
        private Long id;
        private String username;
        private BigDecimal totalPrice;
        private List<OrderItemDto> items;
        private String phoneNumber;
        private String address;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withTotalPrice(BigDecimal val) {
            totalPrice = val;
            return this;
        }

        public Builder withItems(List<OrderItemDto> val) {
            items = val;
            return this;
        }

        public Builder withPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }
    }
}