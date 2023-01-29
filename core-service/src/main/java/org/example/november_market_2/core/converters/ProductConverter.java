package org.example.november_market_2.core.converters;

import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {
        return ProductDto.Builder.newBuilder()
                .withId(p.getId())
                .withTitle(p.getTitle())
                .withPrice(p.getPrice())
                .withCategoryTitle(p.getCategory().getTitle())
                .build();
    }
}


