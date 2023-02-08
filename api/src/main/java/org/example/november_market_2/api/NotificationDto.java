package org.example.november_market_2.api;

import java.time.LocalDate;

public class NotificationDto {
    private Long id;

    private Long productId;

    private String text;

    private LocalDate createdAt;

    public NotificationDto() {
    }

    public NotificationDto(Long id, Long productId, String text, LocalDate createdAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.createdAt = createdAt;
    }

    private NotificationDto(Builder builder) {
        id = builder.id;
        productId = builder.productId;
        text = builder.text;
        createdAt = builder.createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public static final class Builder {
        private Long id;
        private Long productId;
        private String text;
        private LocalDate createdAt;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withProductId(Long val) {
            productId = val;
            return this;
        }

        public Builder withText(String val) {
            text = val;
            return this;
        }

        public Builder withCreatedAt(LocalDate val) {
            createdAt = val;
            return this;
        }

        public NotificationDto build() {
            return new NotificationDto(this);
        }
    }
}

