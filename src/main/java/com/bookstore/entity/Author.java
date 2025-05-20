package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    // Helper method to add a book
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    // Helper method to remove a book
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
