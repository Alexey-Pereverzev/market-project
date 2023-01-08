package org.example.november_market_2.cart.utils;

import lombok.Data;
import org.example.november_market_2.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<LineItem> items;
    private BigDecimal totalPrice;

    public Cart () {
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }
    public void add(ProductDto p) {
        for (LineItem item : items) {
            if (item.getProductId().equals(p.getId())) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        LineItem item = new LineItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(item);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }

    public void decrease(ProductDto p) {
        for (LineItem item : items) {
            if (item.getProductId().equals(p.getId())) {
                if (item.getQuantity()>1) {
                    item.decrementQuantity();
                } else {
                    items.remove(item);
                }
                recalculate();
                return;
            }
        }
    }
}

