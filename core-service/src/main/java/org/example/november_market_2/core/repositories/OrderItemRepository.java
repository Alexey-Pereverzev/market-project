package org.example.november_market_2.core.repositories;

import org.example.november_market_2.core.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select item from OrderItem item where item.order.id = ?1")
    List<OrderItem> findByOrderId(Long orderId);
}
