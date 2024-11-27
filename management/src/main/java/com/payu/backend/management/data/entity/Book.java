package com.payu.backend.management.data.entity;

import com.payu.backend.management.data.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isbn_number")
    private String isbnNumber;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "book_type")
    @Enumerated(EnumType.STRING)
    private BookType bookType;

    private String author;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

}
