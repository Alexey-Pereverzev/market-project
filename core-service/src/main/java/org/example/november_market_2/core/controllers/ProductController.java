package org.example.november_market_2.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.PageDto;
import org.example.november_market_2.api.ProductDto;
import org.example.november_market_2.core.converters.PageConverter;
import org.example.november_market_2.core.converters.ProductConverter;
import org.example.november_market_2.core.entities.Product;
import org.example.november_market_2.core.exceptions.AppError;
import org.example.november_market_2.core.exceptions.ResourceNotFoundException;
import org.example.november_market_2.core.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Методы работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;


    @GetMapping
    public PageDto getAllProducts(
            @RequestParam(name = "p", defaultValue = "1")
            @Parameter(description = "Номер страницы", required = true) Integer page,
            @RequestParam(name = "page_size", defaultValue = "5")
            @Parameter(description = "Номер страницы", required = true) Integer pageSize,
            @RequestParam(name = "productFilter", required = false)
            @Parameter(description = "Фильтр части названия продукта", required = false)  String titlePart,
            @RequestParam(name = "priceMinFilter", required = false)
            @Parameter(description = "Фильтр по мин цене продукта", required = false)  String minPrice,
            @RequestParam(name = "priceMaxFilter", required = false)
            @Parameter(description = "Фильтр по макс цене продукта", required = false)  String maxPrice
    ) {
        if (page < 1) {
            page = 1;
        }
        Optional<String> productFilter = (titlePart!=null)?(Optional.of(titlePart)):Optional.empty();
        Optional<String> minPriceStr = (minPrice!=null)?(Optional.of(minPrice)):Optional.empty();
        Optional<String> maxPriceStr = (maxPrice!=null)?(Optional.of(maxPrice)):Optional.empty();

        Page<ProductDto> p = productService.findWithFilter(page-1, pageSize, productFilter, minPriceStr, maxPriceStr);

        return pageConverter.entityToDto(p);
    }


    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Optional<Product> p = productService.findById(id);
        return productConverter.entityToDto(p.orElseThrow(() ->
                new ResourceNotFoundException("Продукт с id = " + id + " не найден")));
    }

    @Operation(
            summary = "Запрос на создание нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @Operation(
            summary = "Запрос на удаление продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно удален", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @Operation(
            summary = "Запрос на изменение продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно изменен", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }
}