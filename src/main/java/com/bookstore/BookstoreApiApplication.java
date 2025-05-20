package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BookstoreApiApplication {

    public static void main(String[] args) {
        System.err.println("This is from the before running line 10 ...............");
        SpringApplication.run(BookstoreApiApplication.class, args);
        System.err.println("This is from the After running line 12 ...............");
    }
    
    
}
