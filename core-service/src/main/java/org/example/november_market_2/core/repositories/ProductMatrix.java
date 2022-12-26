package org.example.november_market_2.core.repositories;

import org.example.november_market_2.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMatrix extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.title = ?1")
    Optional<Product> findByTitle(String title);
}
