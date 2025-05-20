package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // ✅ Public - Get all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // ✅ Admin - Add author
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Author name is required");
        }
        Author savedAuthor = authorService.saveOrUpdateAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    // ✅ Public - Get author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    // ✅ Admin - Delete author
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully");
    }

    // ✅ Admin - Update author
 // ✅ Admin - Update author
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedAuthor.getName() == null || updatedAuthor.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Author name is required");
        }

        existingAuthor.setName(updatedAuthor.getName());
        Author saved = authorService.saveOrUpdateAuthor(existingAuthor);
        return ResponseEntity.ok(saved);
    }

}
