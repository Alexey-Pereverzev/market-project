package market_homework.services;

import market_homework.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import market_homework.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
