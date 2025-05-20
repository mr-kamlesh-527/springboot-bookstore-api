//package com.bookstore;
//
////package com.bookstore.util;
//
//import com.bookstore.dto.BookDto;
//import com.bookstore.entity.Author;
//import com.bookstore.entity.Book;
//
//public class BookConverter {
//
//    public static Book dtoToEntity(BookDto dto) {
//        Book book = new Book();
//        book.setId(dto.getId());
//        book.setIsbn(dto.getIsbn());
//        book.setTitle(dto.getTitle());
//        book.setPrice(dto.getPrice());
//        book.setStock(dto.getStock());
//        book.setStockQuantity(dto.getStockQuantity());
//
//        if (dto.getAuthorId() != null) {
//            Author author = new Author();
//            author.setId(dto.getAuthorId());
//            book.setAuthor(author);
//        }
//
//        return book;
//    }
//
//    public static BookDto entityToDto(Book book) {
//        BookDto dto = new BookDto();
//        dto.setId(book.getId());
//        dto.setIsbn(book.getIsbn());
//        dto.setTitle(book.getTitle());
//        dto.setPrice(book.getPrice());
//        dto.setStock(book.getStock());
//        dto.setStockQuantity(book.getStockQuantity());
//        if (book.getAuthor() != null) {
//            dto.setAuthorId(book.getAuthor().getId());
//        }
//        return dto;
//    }
//}
