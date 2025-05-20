package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.service.AuthorService;
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
@RequestMapping("/api/admin")
public class AdminController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final OrderService orderService;

    @Autowired
    public AdminController(BookService bookService, AuthorService authorService, OrderService orderService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.orderService = orderService;
    }

    // Add a new book
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // Update an existing book
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book); // ✅ FIXED: removed Optional
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book by ID
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // Add or update an author
    @PostMapping("/authors")
    public ResponseEntity<Author> addOrUpdateAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveOrUpdateAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
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
