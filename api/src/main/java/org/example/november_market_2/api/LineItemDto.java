package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель элемента корзины")
public class LineItemDto {

    @Schema(description = "ID товара", required = true, example = "1")
    private Long productId;

    @Schema(description = "Название товара", required = true, maxLength = 255, minLength = 3,
            example = "Набор пробников для кошек")
    private String productTitle;

    @Schema(description = "Количество", required = true, example = "5")
    private int quantity;

    @Schema(description = "Цена 1 единицы товара", required = true, example = "1290.00")
    private BigDecimal pricePerProduct;

    @Schema(description = "Цена выбранного количества товара", required = true, example = "6450.00")
    private BigDecimal price;

    private LineItemDto(Builder builder) {
        setProductId(builder.productId);
        setProductTitle(builder.productTitle);
        setQuantity(builder.quantity);
        setPricePerProduct(builder.pricePerProduct);
        setPrice(builder.price);
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public LineItemDto() {
    }

    public LineItemDto(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }


    public static final class Builder {
        private Long productId;
        private String productTitle;
        private int quantity;
        private BigDecimal pricePerProduct;
        private BigDecimal price;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withProductId(Long val) {
            productId = val;
            return this;
        }

        public Builder withProductTitle(String val) {
            productTitle = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withPricePerProduct(BigDecimal val) {
            pricePerProduct = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public LineItemDto build() {
            return new LineItemDto(this);
        }
    }
}

