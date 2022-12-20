package market_homework.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import market_homework.converters.LineItemConverter;
import market_homework.utils.LineItem;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
public class CartDto {
    private final LineItemConverter lineItemConverter;

    private List<LineItemDto> dtoItems;
    private BigDecimal totalPrice;

    public void setDtoList(List<LineItem> itemsList) {
        for (LineItem item : itemsList) {
            dtoItems.add(lineItemConverter.entityToDto(item));
        }
    }
}
