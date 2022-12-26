package org.example.november_market_2.core.converters;

import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }
}

