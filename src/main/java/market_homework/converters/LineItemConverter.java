package market_homework.converters;


import market_homework.dtos.LineItemDto;
import org.springframework.stereotype.Component;
import market_homework.utils.LineItem;

@Component
public class LineItemConverter {
    public LineItemDto entityToDto(LineItem l) {
        LineItemDto lineItemDto = new LineItemDto();
        lineItemDto.setCost(l.getCost());
        lineItemDto.setQuantity(l.getQuantity());
        lineItemDto.setProductId(l.getProductId());
        lineItemDto.setProductTitle(l.getProductTitle());
        lineItemDto.setCostPerProduct(l.getCostPerProduct());
        return lineItemDto;
    }
}

