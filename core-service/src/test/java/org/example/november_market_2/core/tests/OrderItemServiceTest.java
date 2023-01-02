package org.example.november_market_2.core.tests;

import org.example.november_market_2.api.LineItemDto;
import org.example.november_market_2.core.entities.OrderItem;
import org.example.november_market_2.core.entities.Product;
import org.example.november_market_2.core.services.OrderItemService;
import org.example.november_market_2.core.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(classes = OrderItemService.class)
    public class OrderItemServiceTest {
    @Autowired
    OrderItemService orderItemService;

    @MockBean
    private ProductService productService;

    @Test
    public void createFromLineItemTest() {
        LineItemDto lineItemDto = new LineItemDto();
        lineItemDto.setProductId(10L);
        lineItemDto.setProductTitle("Жевательные добавки для взрослых собак");
        lineItemDto.setQuantity(5);
        lineItemDto.setPricePerProduct(BigDecimal.valueOf(1622.50));
        lineItemDto.setPrice(BigDecimal.valueOf(8112.50));

        Product product = new Product();
        product.setId(10L);
        product.setTitle("Жевательные добавки для взрослых собак");
        product.setPrice(BigDecimal.valueOf(1622.50));
        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findById(10L);

        OrderItem orderItem = orderItemService.createFromLineItem(lineItemDto);
        Assertions.assertEquals(10L, orderItem.getProduct().getId());
        Assertions.assertEquals(BigDecimal.valueOf(1622.50), orderItem.getPricePerProduct());
        Assertions.assertEquals(BigDecimal.valueOf(8112.50), orderItem.getPrice());
        Assertions.assertEquals(5, orderItem.getQuantity());
        Assertions.assertEquals("Жевательные добавки для взрослых собак", orderItem.getProduct().getTitle());
    }
}
