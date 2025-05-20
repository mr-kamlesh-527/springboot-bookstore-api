//package com.bookstore.service;
//
//import com.bookstore.entity.Book;
//import com.bookstore.repository.BookRepository;
//import com.bookstore.service.BookService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    void testGetAllBooks() {
//        List<Book> mockBooks = List.of(new Book(1L, "Spring", "123", 10));
//        Mockito.when(bookRepository.findAll()).thenReturn(mockBooks);
//
//        List<Book> result = bookService.getAllBooks();
//
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals("Spring", result.get(0).getTitle());
//    }
//}
