package org.example.november_market_2.core.repositories;

import org.example.november_market_2.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMatrix extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.title = ?1")
    Optional<Product> findByTitle(String title);

    @Query("select p from Product p " +
            "where (p.title like :title)" +
            "and   (p.price >= :minPrice or :minPrice is null)" +
            "and   (p.price <= :maxPrice or :maxPrice is null)")
    List<Product> findWithFilter(@Param("title") String productFilter,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);
}
