package market_homework.converters;


import market_homework.dtos.ProductDto;
import market_homework.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDto entityToDto(ProductEntity p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }
}

