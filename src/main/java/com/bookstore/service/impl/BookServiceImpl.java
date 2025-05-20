package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           OrderItemRepository orderItemRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Book addBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setStockQuantity(bookDetails.getStockQuantity());

        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

        // Delete related order_items first
        orderItemRepository.deleteByBook(book);

        // Delete the book
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book updateStock(Long bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setStockQuantity(book.getStockQuantity() + quantity);
        return bookRepository.save(book);
    }
}
