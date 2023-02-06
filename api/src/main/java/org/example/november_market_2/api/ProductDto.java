package org.example.november_market_2.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель товара")
public class ProductDto {
    @Schema(description = "ID товара", required = true, example = "1")
    private Long id;

    @Schema(description = "Название товара", required = true, maxLength = 255, minLength = 3,
            example = "Набор пробников для кошек")
    private String title;

    @Schema(description = "Цена товара", required = true, example = "1290.00")
    private BigDecimal price;

    @Schema(description = "Категория товара", required = true, example = "Pet_Supplements")
    private String categoryTitle;

    private ProductDto(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setPrice(builder.price);
        setCategoryTitle(builder.categoryTitle);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private BigDecimal price;
        private String categoryTitle;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder withCategoryTitle(String val) {
            categoryTitle = val;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }
}