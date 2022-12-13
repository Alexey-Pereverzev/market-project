package market_homework.converters;


import market_homework.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import market_homework.utils.Cart;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final LineItemConverter lineItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto(lineItemConverter);
        cartDto.setDtoList(cart.getItems());
        cartDto.setTotalCost(cart.getTotalCost());
        return cartDto;
    }
}

