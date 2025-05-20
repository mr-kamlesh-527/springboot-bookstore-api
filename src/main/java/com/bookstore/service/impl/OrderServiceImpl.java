package com.bookstore.service.impl;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Order placeOrder(Long userId, List<Long> bookIds) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Order placeOrder(Order order) {
        // ✅ 1. Set the User using username
        String username = order.getUsername();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        order.setUser(user);

        // ✅ 2. Set Order reference in each OrderItem and load Book from DB
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order); // 🔁 Back-reference
            Long bookId = item.getBook().getId();
            Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
            item.setBook(book);
        }

        return orderRepository.save(order); // ✅ Saves order and order_items with correct mappings
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(User::getId)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> getOrdersByCustomer(String username, Pageable pageable) {
        return orderRepository.findByUser_Username(username, pageable);
    }
}