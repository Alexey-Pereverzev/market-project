package org.example.november_market_2.core.tests;

import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.converters.ProductConverter;
import org.example.november_market_2.core.entities.Category;
import org.example.november_market_2.core.repositories.ProductMatrix;
import org.example.november_market_2.core.services.CategoryService;
import org.example.november_market_2.core.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    @MockBean
    private ProductMatrix matrix;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductConverter productConverter;

    @Test
    public void createNewProductTest() {
        Category category = new Category();
        category.setId(3L);
        category.setTitle("Pet_Accessories");
        category.setProducts(Collections.emptyList());
        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findByTitle("Pet_Accessories");

        ProductDto productDto = new ProductDto(null, "Мячик для собак", BigDecimal.valueOf(300.0),
                "Pet_Accessories");
        productService.createNewProduct(productDto);

        Mockito.verify(matrix, Mockito.times(1)).save(ArgumentMatchers.any());


    }
}
