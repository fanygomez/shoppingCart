package com.shoppingCart.repositories;

import com.shoppingCart.models.Order;
import com.shoppingCart.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByIdAndUserId(Long orderId, Long userId);
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);
}
