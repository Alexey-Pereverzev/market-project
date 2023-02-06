package org.example.november_market_2.core.utils;

import org.springframework.context.ApplicationEvent;


public class ProductDeleteEvent extends ApplicationEvent {
    private final String productTitle;

    private final Long productId;

    public ProductDeleteEvent(Object source, Long productId, String productTitle) {
        super(source);
        this.productTitle = productTitle;
        this.productId = productId;
    }


    public String getProductTitle() {
        return productTitle;
    }

    public Long getProductId() {
        return productId;
    }
}


