package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель элемента заказа")
public class OrderItemDto {

    @Schema(description = "ID заказа", required = true, example = "14")
    private Long orderId;

    @Schema(description = "ID товара", required = true, example = "1")
    private Long productId;

    @Schema(description = "Название товара", required = true, maxLength = 255, minLength = 3,
            example = "Набор пробников для кошек")
    private String productTitle;

    @Schema(description = "Цена 1 единицы товара", required = true, example = "1290.00")
    private BigDecimal pricePerProduct;

    @Schema(description = "Цена выбранного количества товара", required = true, example = "6450.00")
    private BigDecimal price;

    @Schema(description = "Количество", required = true, example = "5")
    private int quantity;

    public OrderItemDto() {
    }

    public OrderItemDto(Long orderId, Long productId, String productTitle, BigDecimal pricePerProduct, BigDecimal price,
                        int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
