package com.bookstore.service;

import com.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, List<Long> bookIds);                  // ✅ Useful for simplified order placing
    Order placeOrder(Order order);                                      // ✅ Flexible for full Order submission
    List<Order> getOrderHistory(Long userId);                           // ✅ For customer order history
    Long getUserIdByUsername(String username);                          // ✅ Helps fetch user ID when only username is available
    Page<Order> getAllOrders(Pageable pageable);                        // ✅ For Admin & Manager - view all orders
    Page<Order> getOrdersByCustomer(String username, Pageable pageable);// ✅ For Customer - paginated order history
}