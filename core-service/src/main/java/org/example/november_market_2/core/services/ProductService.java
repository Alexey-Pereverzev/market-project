package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.entities.Product;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.example.november_market_2.core.repositories.ProductMatrix;
import org.example.november_market_2.core.soap.products.ProductSoap;
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

    public List<Product> findAll() {
        return matrix.findAll();
    }

    public void deleteById(Long id) {
        matrix.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: "
                        + productDto.getCategoryTitle() + " не найдена")));
        matrix.save(product);
    }

    public Optional<Product> findById(Long id) {
        return matrix.findById(id);
    }

    public static final Function<Product, ProductSoap> functionEntityToSoap = pe -> {
        ProductSoap p = new ProductSoap();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        p.setCategoryTitle(pe.getCategory().getTitle());
        return p;
    };

    public ProductSoap getByTitle(String title) {
        return matrix.findByTitle(title).map(functionEntityToSoap).get();
    }

    public List<ProductSoap> getAllProducts() {
        return matrix.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}

