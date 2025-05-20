package com.bookstore.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long bookId;
    private int quantity;
}