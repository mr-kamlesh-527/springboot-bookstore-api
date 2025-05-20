package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.service.BookService;
import com.bookstore.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    // Browse all available books with pagination and sorting
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> browseBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {
        Page<Book> books = bookService.getBooks(PageRequest.of(page, size).withSort(org.springframework.data.domain.Sort.by(sortBy)));
        return ResponseEntity.ok(books);
    }

    // Place an order
    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // View order history of the logged-in customer with pagination
    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> viewOrderHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String customerUsername) { // or get username from security context
        Page<Order> orders = orderService.getOrdersByCustomer(customerUsername, PageRequest.of(page, size));
        return ResponseEntity.ok(orders);
    }
}
