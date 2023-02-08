package org.example.november_market_2.core.repositories;

import org.example.november_market_2.core.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.createdAt > ?1 order by n.id desc")
    List<Notification> findAllFromCleared(LocalDate cleared);

    List<Notification> findTop100ByOrderByIdDesc();

    List<Notification> findTop1ByProductId(Long productId);

}
