package org.example.november_market_2.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "notification_text")
    private String text;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    private Notification(Builder builder) {
        setId(builder.id);
        setProductId(builder.productId);
        setText(builder.text);
        setCreatedAt(builder.createdAt);
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


        public Notification build() {
            return new Notification(this);
        }
    }
}
