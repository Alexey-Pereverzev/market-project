package org.example.november_market_2.core.services;


import lombok.RequiredArgsConstructor;
import org.example.november_market_2.core.entities.Category;
import org.example.november_market_2.core.repositories.CategoryRepository;
import org.example.november_market_2.core.soap.categories.CategorySoap;
import org.springframework.stereotype.Service;

import java.util.IdentityHashMap;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private IdentityHashMap<String, Category> identityHashMap = new IdentityHashMap<>();

    public Optional<Category> findByTitle(String title) {
        if (identityHashMap.containsKey(title)) {
            return Optional.ofNullable(identityHashMap.get(title));
        } else {
            Optional<Category> category = categoryRepository.findByTitle(title);
            category.ifPresent(value -> identityHashMap.put(title, value));
            return category;
        }
    }

    public static final Function<Category, CategorySoap> functionEntityToSoap = ce -> {
        CategorySoap c = new CategorySoap();
        c.setTitle(ce.getTitle());
        ce.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(p -> c.getProducts().add(p));
        return c;
    };

    public CategorySoap getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}

