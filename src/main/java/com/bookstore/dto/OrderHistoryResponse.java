package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderHistoryResponse {
    private Long orderId;
    private String bookIsbn;
    private String bookTitle;
    private int quantity;
    private LocalDateTime orderDate;
    private String status; // Optional, if not needed set to null or "Pending"
} 