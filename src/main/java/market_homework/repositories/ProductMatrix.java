package market_homework.repositories;

import market_homework.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMatrix extends JpaRepository<ProductEntity, Long> {
    @Query("select p from ProductEntity p where p.title = ?1")
    Optional<ProductEntity> findByTitle(String title);
}



