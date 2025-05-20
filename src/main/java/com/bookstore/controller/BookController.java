package com.bookstore.controller;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @Operation(summary = "Add a new book", description = "Add a book. ADMIN role required.")
    @ApiResponse(responseCode = "200", description = "Book added successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/books")
    public Book addBook(@RequestBody Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + book.getAuthor().getId()));
            book.setAuthor(author);
        }
        return bookService.addBook(book);
    }

    @Operation(summary = "Update an existing book", description = "Update a book by ID. ADMIN role required.")
    @ApiResponse(responseCode = "200", description = "Book updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @Operation(summary = "Delete a book", description = "Delete a book by ID. ADMIN role required.")
    @ApiResponse(responseCode = "204", description = "Book deleted successfully")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @Operation(summary = "Get paginated list of books", description = "Get all books with pagination. Accessible to ADMIN and MANAGER roles.")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully",
            content = @Content(mediaType = "application/json"))
    @GetMapping
    public Page<BookDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getBooks(pageable);

        return bookPage.map(book -> {
            BookDto dto = new BookDto();
            dto.setIsbn(book.getIsbn());
            dto.setTitle(book.getTitle());
            dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
            dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : "Unknown");
            dto.setPrice(book.getPrice());
            dto.setStock(book.getStockQuantity());
            return dto;
        });
    }

    @Operation(summary = "Get paginated list of books for customer", description = "Get books with pagination for customer view.")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/customer/books")
    public Page<BookDto> getAllBooksForCustomer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getBooks(pageable);

        return bookPage.map(book -> {
            BookDto dto = new BookDto();
            dto.setIsbn(book.getIsbn());
            dto.setTitle(book.getTitle());
            dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
            dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : "Unknown");
            dto.setPrice(book.getPrice());
            dto.setStock(book.getStockQuantity());
            return dto;
        });
    }

    @Operation(summary = "Get all books for manager", description = "Get all books without pagination. MANAGER role required.")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager/books")
    public java.util.List<Book> getAllBooksForManager() {
        return bookService.getAllBooks();
    }
}
