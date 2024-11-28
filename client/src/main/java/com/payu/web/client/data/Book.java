package com.payu.web.client.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.payu.web.client.data.enums.BookType;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private Long id;
    private String name;
    private String isbnNumber;
    private String publishDate;
    private Double price;
    private BookType bookType;
    private String author;
    private Long numberOfPages;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
