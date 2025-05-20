package com.bookstore.service;

import com.bookstore.entity.Author;
import java.util.List;

public interface AuthorService {
    Author saveOrUpdateAuthor(Author author);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    void deleteAuthor(Long id);
}
