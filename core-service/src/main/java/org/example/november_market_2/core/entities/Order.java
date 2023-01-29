package org.example.november_market_2.core.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
        items = new ArrayList<>();
    }

    private Order(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setTotalPrice(builder.totalPrice);
        setItems(builder.items);
        setPhoneNumber(builder.phoneNumber);
        setAddress(builder.address);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
    }


    public static final class Builder {
        private Long id;
        private String username;
        private BigDecimal totalPrice;
        private List<OrderItem> items;
        private String phoneNumber;
        private String address;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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

        public Builder withItems(List<OrderItem> val) {
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

        public Builder withCreatedAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder withUpdatedAt(LocalDateTime val) {
            updatedAt = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}


