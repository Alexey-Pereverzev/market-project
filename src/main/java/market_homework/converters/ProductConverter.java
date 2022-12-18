package market_homework.converters;


import market_homework.dtos.ProductDto;
import market_homework.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setCost(p.getCost());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }
}

