package org.example.november_market_2.cart.converters;

import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.cart.utils.LineItem;
import org.springframework.stereotype.Component;

@Component
public class LineItemConverter {
    public LineItemDto entityToDto(LineItem l) {
        return new LineItemDto(l.getProductId(), l.getProductTitle(), l.getQuantity(), l.getPricePerProduct(), l.getPrice());
    }
}
