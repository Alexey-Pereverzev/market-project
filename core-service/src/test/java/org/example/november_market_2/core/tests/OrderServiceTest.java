package org.example.november_market_2.core.tests;

import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.core.entities.OrderItem;
import org.example.november_market_2.core.entities.Product;
import org.example.november_market_2.core.repositories.OrderItemRepository;
import org.example.november_market_2.core.repositories.OrderRepository;
import org.example.november_market_2.core.services.OrderItemService;
import org.example.november_market_2.core.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(classes = OrderService.class)
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @Test
    public void createNewOrderTest() {
        LineItemDto lineItem1 = new LineItemDto();
        lineItem1.setProductId(1L);
        lineItem1.setProductTitle("Набор пробников для кошек");
        lineItem1.setQuantity(3);
        lineItem1.setPrice(BigDecimal.valueOf(3749.70));
        lineItem1.setPricePerProduct(BigDecimal.valueOf(1249.90));

        LineItemDto lineItem2 = new LineItemDto();
        lineItem2.setProductId(2L);
        lineItem2.setProductTitle("Печенье для собак вкус яблока");
        lineItem2.setQuantity(5);
        lineItem2.setPrice(BigDecimal.valueOf(2749.50));
        lineItem2.setPricePerProduct(BigDecimal.valueOf(549.90));

        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Набор пробников для кошек");
        product.setPrice(BigDecimal.valueOf(1249.90));
        orderItem.setProduct(product);
        orderItem.setQuantity(3);
        orderItem.setPricePerProduct(BigDecimal.valueOf(1249.90));
        orderItem.setPrice(BigDecimal.valueOf(3749.70));
        Mockito.doReturn(orderItem)
                .when(orderItemService)
                .createFromLineItem(lineItem1);

        OrderItem orderItem2 = new OrderItem();
        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Печенье для собак вкус яблока");
        product2.setPrice(BigDecimal.valueOf(549.90));
        orderItem2.setProduct(product);
        orderItem2.setQuantity(5);
        orderItem2.setPricePerProduct(BigDecimal.valueOf(549.90));
        orderItem2.setPrice(BigDecimal.valueOf(2749.50));
        Mockito.doReturn(orderItem2)
                .when(orderItemService)
                .createFromLineItem(lineItem2);

        orderService.createNewOrder(List.of(lineItem1, lineItem2), "bob");
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

}
