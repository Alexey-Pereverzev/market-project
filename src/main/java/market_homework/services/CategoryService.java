package market_homework.services;


import lombok.RequiredArgsConstructor;
import market_homework.entities.CategoryEntity;
import market_homework.repositories.CategoryRepository;
import market_homework.soap.categories.Category;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<CategoryEntity> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public static final Function<CategoryEntity, Category> functionEntityToSoap = ce -> {
        Category c = new Category();
        c.setTitle(ce.getTitle());
        ce.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(p -> c.getProducts().add(p));
        return c;
    };

    public Category getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }


}
