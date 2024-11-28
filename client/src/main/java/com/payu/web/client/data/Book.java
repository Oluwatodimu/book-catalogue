package com.payu.web.client.data;

import com.payu.web.client.data.enums.BookType;

import java.time.Instant;
import java.time.LocalDate;

public class Book {
    private Long id;
    private String name;
    private String isbnNumber;
    private LocalDate publishDate;
    private Double price;
    private BookType bookType;
    private String author;
    private Long numberOfPages;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
