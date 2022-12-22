package org.example.november_market_2.core.repositories;

import org.example.november_market_2.core.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.title = ?1")
    Optional<Category> findByTitle(String title);
}
