package market_homework.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;

    public void incrementQuantity() {
        quantity++;
        cost = cost.add(costPerProduct);
    }

    public void decrementQuantity() {
        quantity--;
        cost = cost.subtract(costPerProduct);
    }
}
