package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.*;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorBookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public void createAuthorAndBook() {
        Author author = new Author();
        author.setName("J.K. Rowling");
        authorRepository.save(author);

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle("Harry Potter");
        book.setIsbn("123-456");
        book.setPrice(500.0);
        book.setStockQuantity(10);
        bookRepository.save(book);
    }
}
