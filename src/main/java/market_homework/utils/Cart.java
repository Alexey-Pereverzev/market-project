package market_homework.utils;

import lombok.Data;
import market_homework.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<LineItem> items;
    private BigDecimal totalPrice;

    public void add(ProductEntity p) {
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

    public void decrease(ProductEntity p) {
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

