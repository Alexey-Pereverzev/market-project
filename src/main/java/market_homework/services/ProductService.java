package market_homework.services;


import market_homework.dtos.ProductDto;
import market_homework.entities.Product;
import market_homework.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import market_homework.repositories.ProductMatrix;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMatrix matrix;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return matrix.findAll();
    }

    public void deleteById(Long id) {
        matrix.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: "
                        + productDto.getCategoryTitle() + " не найдена")));
        matrix.save(product);
    }

    public Optional<Product> findById(Long id) {
        return matrix.findById(id);
    }
}

