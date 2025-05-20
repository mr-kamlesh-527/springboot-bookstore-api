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
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    // View all books (pagination and sorting)
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> viewAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {
        Page<Book> books = bookService.getBooks(PageRequest.of(page, size).withSort(org.springframework.data.domain.Sort.by(sortBy)));
        return ResponseEntity.ok(books);
    }

    // Update stock for a book
    @PutMapping("/books/{bookId}/stock")
    public ResponseEntity<Book> updateStock(
            @PathVariable Long bookId,
            @RequestParam int quantity) {
        Book updatedBook = bookService.updateStock(bookId, quantity);
        return ResponseEntity.ok(updatedBook);
    }

    // View all orders with pagination
    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> viewAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Order> orders = orderService.getAllOrders(PageRequest.of(page, size));
        return ResponseEntity.ok(orders);
    }
}
