package org.example.november_market_2.core.utils;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;


public class ProductChangeEvent extends ApplicationEvent {
    private final String productTitle;
    private final BigDecimal newPrice;

    private final Long productId;

    public ProductChangeEvent(Object source, Long productId, String productTitle, BigDecimal newPrice) {
        super(source);
        this.productTitle = productTitle;
        this.newPrice = newPrice;
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public Long getProductId() {
        return productId;
    }
}









