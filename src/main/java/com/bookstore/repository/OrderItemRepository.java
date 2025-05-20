package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByBook(Book book); // ✅ Custom method to delete all order_items of a book
}
