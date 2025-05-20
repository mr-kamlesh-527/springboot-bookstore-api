package com.bookstore.service;

import com.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);

    // Pagination & sorting support
    Page<Book> getBooks(Pageable pageable);

    Book updateStock(Long bookId, int quantity);
}
