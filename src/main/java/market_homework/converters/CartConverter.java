package market_homework.converters;


import lombok.RequiredArgsConstructor;
import market_homework.dtos.CartDto;
import market_homework.utils.Cart;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final LineItemConverter lineItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto(lineItemConverter);
        cartDto.setDtoList(cart.getItems());
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }
}

