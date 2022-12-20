package market_homework.services;


import lombok.RequiredArgsConstructor;
import market_homework.dtos.ProductDto;
import market_homework.entities.ProductEntity;
import market_homework.exceptions.ResourceNotFoundException;
import market_homework.repositories.ProductMatrix;
import market_homework.soap.products.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMatrix matrix;
    private final CategoryService categoryService;

    public List<ProductEntity> findAll() {
        return matrix.findAll();
    }

    public void deleteById(Long id) {
        matrix.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: "
                        + productDto.getCategoryTitle() + " не найдена")));
        matrix.save(product);
    }

    public Optional<ProductEntity> findById(Long id) {
        return matrix.findById(id);
    }

    public static final Function<ProductEntity, Product> functionEntityToSoap = pe -> {
        Product p = new Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        p.setCategoryTitle(pe.getCategory().getTitle());
        return p;
    };

    public Product getByTitle(String title) {
        return matrix.findByTitle(title).map(functionEntityToSoap).get();
    }

    public List<Product> getAllProducts() {
        return matrix.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}

