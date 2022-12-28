package org.example.november_market_2.cart.tests;

import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.cart.integrations.ProductServiceIntegration;
import org.example.november_market_2.cart.services.CartService;
import org.example.november_market_2.cart.utils.LineItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest(classes = CartService.class)
public class CartServiceTests {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void addToCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setPrice(BigDecimal.valueOf(1622.50));
        productDto.setTitle("Жевательные добавки для взрослых собак");
        productDto.setCategoryTitle("Pet_Supplements");
        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(10L);

        LineItem lineItem1 = new LineItem();
        lineItem1.setProductId(1L);
        lineItem1.setProductTitle("Набор пробников для кошек");
        lineItem1.setQuantity(1);
        lineItem1.setPrice(BigDecimal.valueOf(1249.90));
        lineItem1.setPricePerProduct(BigDecimal.valueOf(1249.90));

        LineItem lineItem2 = new LineItem();
        lineItem2.setProductId(2L);
        lineItem2.setProductTitle("Печенье для собак вкус яблока");
        lineItem2.setQuantity(1);
        lineItem2.setPrice(BigDecimal.valueOf(549.90));
        lineItem2.setPricePerProduct(BigDecimal.valueOf(549.90));

        cartService.getCurrentCart().clear();
        cartService.getCurrentCart().getItems().add(lineItem1);
        cartService.getCurrentCart().getItems().add(lineItem2);
        cartService.getCurrentCart().setTotalPrice(BigDecimal.ZERO.add(lineItem1.getPrice()).add(lineItem2.getPrice()));

        cartService.addToCart(10L);
        Assertions.assertEquals(3, cartService.getCurrentCart().getItems().size());
        Assertions.assertEquals(BigDecimal.valueOf(3422.30), cartService.getCurrentCart().getTotalPrice());
        Assertions.assertEquals("Жевательные добавки для взрослых собак",
                cartService.getCurrentCart().getItems().get(2).getProductTitle());
    }

    @Test
    public void clearCartTest() {
        LineItem lineItem1 = new LineItem();
        lineItem1.setProductId(1L);
        lineItem1.setProductTitle("Набор пробников для кошек");
        lineItem1.setQuantity(1);
        lineItem1.setPrice(BigDecimal.valueOf(1249.90));
        lineItem1.setPricePerProduct(BigDecimal.valueOf(1249.90));

        LineItem lineItem2 = new LineItem();
        lineItem2.setProductId(2L);
        lineItem2.setProductTitle("Печенье для собак вкус яблока");
        lineItem2.setQuantity(1);
        lineItem2.setPrice(BigDecimal.valueOf(549.90));
        lineItem2.setPricePerProduct(BigDecimal.valueOf(549.90));

        cartService.getCurrentCart().clear();
        cartService.getCurrentCart().getItems().add(lineItem1);
        cartService.getCurrentCart().getItems().add(lineItem2);
        cartService.getCurrentCart().setTotalPrice(BigDecimal.ZERO.add(lineItem1.getPrice()).add(lineItem2.getPrice()));

        cartService.clearCart();
        Assertions.assertEquals(0, cartService.getCurrentCart().getItems().size());
        Assertions.assertEquals(BigDecimal.ZERO, cartService.getCurrentCart().getTotalPrice());
    }

    @Test
    public void decreaseTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setPrice(BigDecimal.valueOf(549.90));
        productDto.setTitle("Печенье для собак вкус яблока");
        productDto.setCategoryTitle("Pet_Supplements");
        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(2L);

        ProductDto productDto1 = new ProductDto();
        productDto1.setId(1L);
        productDto1.setPrice(BigDecimal.valueOf(1249.90));
        productDto1.setTitle("Набор пробников для кошек");
        productDto1.setCategoryTitle("Pet_Food");
        Mockito.doReturn(productDto1)
                .when(productServiceIntegration)
                .findById(1L);

        LineItem lineItem1 = new LineItem();
        lineItem1.setProductId(1L);
        lineItem1.setProductTitle("Набор пробников для кошек");
        lineItem1.setQuantity(1);
        lineItem1.setPrice(BigDecimal.valueOf(1249.90));
        lineItem1.setPricePerProduct(BigDecimal.valueOf(1249.90));

        LineItem lineItem2 = new LineItem();
        lineItem2.setProductId(2L);
        lineItem2.setProductTitle("Печенье для собак вкус яблока");
        lineItem2.setQuantity(1);
        lineItem2.setPrice(BigDecimal.valueOf(549.90));
        lineItem2.setPricePerProduct(BigDecimal.valueOf(549.90));

        cartService.getCurrentCart().clear();
        cartService.getCurrentCart().getItems().add(lineItem1);
        cartService.getCurrentCart().getItems().add(lineItem2);
        cartService.getCurrentCart().setTotalPrice(BigDecimal.ZERO.add(lineItem1.getPrice()).add(lineItem2.getPrice()));

        cartService.decrease(2L);
        Assertions.assertEquals(1, cartService.getCurrentCart().getItems().size());
        Assertions.assertEquals(BigDecimal.valueOf(1249.90), cartService.getCurrentCart().getTotalPrice());
        Assertions.assertEquals("Набор пробников для кошек",
                cartService.getCurrentCart().getItems().get(0).getProductTitle());

        cartService.decrease(1L);
        Assertions.assertEquals(0, cartService.getCurrentCart().getItems().size());
        Assertions.assertEquals(BigDecimal.ZERO, cartService.getCurrentCart().getTotalPrice());

        cartService.decrease(2L);
        Assertions.assertEquals(0, cartService.getCurrentCart().getItems().size());
        Assertions.assertEquals(BigDecimal.ZERO, cartService.getCurrentCart().getTotalPrice());
    }

}
