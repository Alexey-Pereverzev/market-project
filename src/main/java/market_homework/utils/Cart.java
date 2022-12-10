package market_homework.utils;


import market_homework.entities.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<LineItem> items;
    private BigDecimal totalCost;

    public void add(Product p) {
        for (LineItem item : items) {
            if (item.getProductId().equals(p.getId())) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        LineItem item = new LineItem(p.getId(), p.getTitle(), 1, p.getCost(), p.getCost());
        items.add(item);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalCost = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        items.forEach(i -> totalCost = totalCost.add(i.getCost()));
    }

    public void decrease(Product p) {
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

