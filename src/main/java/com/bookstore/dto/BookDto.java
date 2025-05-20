package com.bookstore.dto;

import lombok.Data;

@Data
public class BookDto {
    private String isbn;
    private String title;
    private Long authorId;
    private String authorName; // ✅ Added for customer display
    private double price;
    private int stock;
}
