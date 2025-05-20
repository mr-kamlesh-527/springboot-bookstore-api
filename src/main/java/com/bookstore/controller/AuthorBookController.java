package com.example.bookstore.controller;

import com.bookstore.service.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorBookController {

    @Autowired
    private AuthorBookService authorBookService;

    @PostMapping("/create-author-book")
    public String createAuthorAndBook() {
        authorBookService.createAuthorAndBook();
        return "Author and Book created successfully.";
    }
}
