package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.core.entities.Order;
import org.example.november_market_2.core.entities.OrderItem;
import org.example.november_market_2.core.integrations.CartServiceIntegration;
import org.example.november_market_2.core.repositories.OrderItemRepository;
import org.example.november_market_2.core.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private static final PhoneNumberSymbols phoneSymbols = new PhoneNumberSymbols();

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    public List<OrderItem> findItemsByOrderId(Long id) {
        return orderItemRepository.findByOrderId(id);
    }
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public String createNewOrder(String username, String phoneNumber, String address) {
        List<LineItemDto> lineItemDtos = cartServiceIntegration.getCurrentUserCart(username).getItems();
        if (lineItemDtos.isEmpty()){
            return ("Пустой заказ");
        }

        if (acceptablePhoneNumber(phoneNumber)) {               //  если нет недопустимых символов
            phoneNumber = phoneNumber.replace(" ", "").replace("(","").replace(")","")
                    .replace("-","");       //  выкидываем символы кроме цифр
            int length = phoneNumber.length();
            if ((length<7) || (length>13)) {                //  проверяем длину номера - должна быть от 7 до 13
                return ("Некорректный номер телефона");
            }
        } else {
            return ("Некорректный номер телефона");
        }
        Order order = Order.Builder.newBuilder()
                .withPhoneNumber(phoneNumber)
                .withTotalPrice(BigDecimal.ZERO)
                .withUsername(username)
                .withAddress(address)
                .withItems(new ArrayList<>())
                .build();

        for (LineItemDto lineItemDto : lineItemDtos) {
            OrderItem item = orderItemService.createFromLineItem(lineItemDto);
            item.setOrder(order);
            order.getItems().add(item);
            order.setTotalPrice(order.getTotalPrice().add(item.getPrice()));
        }
        orderRepository.save(order);
        return ("");
    }

    public static boolean acceptablePhoneNumber(String phoneNumber) {
        if (phoneNumber==null) {
            return true;
        }
        if (phoneNumber.isBlank()) {
            return true;
        }
        char[] chars = phoneNumber.toCharArray();
        for (char aChar : chars) {
            if (!phoneSymbols.getCharacters().contains(aChar)) {
                return false;
            }
        }
        return true;
    }

    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
