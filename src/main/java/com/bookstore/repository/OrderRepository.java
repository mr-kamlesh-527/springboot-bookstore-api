package com.bookstore.repository;

import com.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get orders for a specific user by user ID
    List<Order> findByUserId(Long userId);

    // Optional: If pagination is needed
    Page<Order> findByUser_Username(String username, Pageable pageable);
}