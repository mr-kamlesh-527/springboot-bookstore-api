package com.bookstore.dto;

import lombok.Data;
import java.util.List;

//src/main/java/com/bookstore/dto/OrderRequestDto.java

@Data
public class OrderRequestDTO {
private String isbn;
private int quantity;
}


