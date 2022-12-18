package market_homework.converters;

import market_homework.dtos.LineItemDto;
import market_homework.utils.LineItem;
import org.springframework.stereotype.Component;

@Component
public class LineItemConverter {
    public LineItemDto entityToDto(LineItem l) {
        LineItemDto lineItemDto = new LineItemDto();
        lineItemDto.setPrice(l.getPrice());
        lineItemDto.setQuantity(l.getQuantity());
        lineItemDto.setProductId(l.getProductId());
        lineItemDto.setProductTitle(l.getProductTitle());
        lineItemDto.setPricePerProduct(l.getPricePerProduct());
        return lineItemDto;
    }
}

