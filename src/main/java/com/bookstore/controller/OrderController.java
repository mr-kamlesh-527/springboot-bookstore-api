package com.bookstore.controller;

import com.bookstore.dto.OrderHistoryResponse;
import com.bookstore.entity.Order;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository; // ✅ Injected

    // 📌 Place an order (Customer only)
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, Authentication authentication) {
        String username = authentication.getName();

        // ✅ Set the user entity (so user_id is not null)
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        order.setUsername(username);  // Optional
        order.setOrderDate(LocalDateTime.now());

        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    // 📌 View order history (Customer only) ✅ UPDATED
    @GetMapping("/history")
    public ResponseEntity<List<OrderHistoryResponse>> viewOrderHistory(Authentication authentication) {
        String username = authentication.getName();
        Long userId = orderService.getUserIdByUsername(username);
        List<Order> orders = orderService.getOrderHistory(userId);

        // 🔍 DEBUG PRINT STATEMENTS
        System.out.println("🔍 Username: " + username);
        System.out.println("🔍 UserId: " + userId);
        System.out.println("🔍 Orders Found: " + orders.size());

        orders.forEach(order -> {
            System.out.println("Order ID: " + order.getId());
            order.getOrderItems().forEach(item -> {
                if (item.getBook() != null) {
                    System.out.println("  📕 Book Title: " + item.getBook().getTitle());
                    System.out.println("  🔢 Quantity: " + item.getQuantity());
                } else {
                    System.out.println("  ⚠️ Book is null for item ID: " + item.getId());
                }
            });
        });

        List<OrderHistoryResponse> response = orders.stream()
                .flatMap(order -> order.getOrderItems().stream()
                        .map(item -> new OrderHistoryResponse(
                                order.getId(),
                                item.getBook() != null ? item.getBook().getIsbn() : "N/A",
                                item.getBook() != null ? item.getBook().getTitle() : "Unknown Book",
                                item.getQuantity(),
                                order.getOrderDate(),
                                "Pending"
                        ))
                )
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // 📌 View all orders - Admin/Manager
    @GetMapping("/all")
    public ResponseEntity<Page<Order>> viewAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    // 📌 Get orders by customer username - Admin/Manager
    @GetMapping("/customer")
    public ResponseEntity<Page<Order>> getOrdersByCustomer(@RequestParam String username, Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(username, pageable));
    }
}