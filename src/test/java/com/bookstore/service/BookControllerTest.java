//package com.bookstore.service;
//
//import com.bookstore.entity.Author;
//import com.bookstore.entity.Book;
//import com.bookstore.repository.BookRepository;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.extension.ExtendWith;
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
//        Author author = new Author(1L, "John Doe", null);
//        Book book = new Book(1L, "Spring", "123", 199.99, 10, author);
//        List<Book> mockBooks = List.of(book);
//
//        when(bookRepository.findAll()).thenReturn(mockBooks);
//
//        List<Book> books = bookService.getAllBooks();
//        assertEquals(1, books.size());
//        assertEquals("Spring", books.get(0).getTitle());
//    }
//}
