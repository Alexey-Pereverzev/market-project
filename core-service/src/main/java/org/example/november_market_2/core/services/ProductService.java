package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.converters.ProductConverter;
import org.example.november_market_2.core.entities.Product;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.example.november_market_2.core.repositories.ProductMatrix;
import org.example.november_market_2.core.repositories.specifications.ProductSpecification;
import org.example.november_market_2.core.soap.products.ProductSoap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMatrix matrix;
    private final CategoryService categoryService;

    private final ProductConverter productConverter;

    public List<Product> findAll() {
        return matrix.findAll();
    }

    public void deleteById(Long id) {
        Optional<Product> p = matrix.findById(id);
        if (p.isPresent()) {
            matrix.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Продукт с id: " +id + " не найден");
        }
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = Product.Builder.newBuilder()
                .withTitle(productDto.getTitle())
                .withPrice(productDto.getPrice())
                .withCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                        .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: "
                                + productDto.getCategoryTitle() + " не найдена")))
                .build();
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

    public static Optional<BigDecimal> strToDecimal(Optional<String> str) {
        if (str.isEmpty()) {
            return Optional.empty();
        }
        try {
            Double.parseDouble(str.get());
            return Optional.of(new BigDecimal(str.get()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public Page<ProductDto> findWithFilter(int page, int pageSize,
                                           Optional<String> productFilter,
                                           Optional<String> minPriceStr,
                                           Optional<String> maxPriceStr) {

        Specification<Product> spec = Specification.where(null);

        if (productFilter.isPresent()) {
            spec = spec.and(ProductSpecification.productLike(productFilter.get()));
        }
        if (minPriceStr.isPresent()) {
            Optional<BigDecimal> minPriceBD = strToDecimal(minPriceStr);
            if (minPriceBD.isPresent()) {
                spec = spec.and(ProductSpecification.minPrice(minPriceBD.get()));
            }
        }
        if (maxPriceStr.isPresent()) {
            Optional<BigDecimal> maxPriceBD = strToDecimal(maxPriceStr);
            if (maxPriceBD.isPresent()) {
                spec = spec.and(ProductSpecification.maxPrice(maxPriceBD.get()));
            }
        }
        return matrix.findAll(spec, PageRequest.of(page, pageSize)).map(productConverter::entityToDto);
    }

    public void save(ProductDto product) {
        Product oldProduct = matrix.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Продукт с id: "
                        + product.getId() + " не найден"));
        oldProduct.setTitle(product.getTitle());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(categoryService.findByTitle(product.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: "
                        + product.getCategoryTitle() + " не найдена")));
        matrix.save(oldProduct);
    }


}
