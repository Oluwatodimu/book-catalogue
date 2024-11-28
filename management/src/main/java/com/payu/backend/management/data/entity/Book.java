package com.payu.backend.management.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payu.backend.management.data.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.Instant;

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

    @NotNull(message = "book name cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-:',().]+$", message = "book name contains potentially harmful characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "isbn cannot be null")
    @Column(name = "isbn_number")
    private String isbnNumber;

    @NotNull(message = "publish date cannot be null")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/\\d{4}$", message = "required date format is dd/MM/yyyy")
    private String publishDate;

    @NotNull(message = "book price cannot be null")
    @PositiveOrZero(message = "price of a book cannot be negative")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "book type cannot be null")
    @Column(name = "book_type")
    @Enumerated(EnumType.STRING)
    private BookType bookType;

    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-:',().]+$", message = "author contains potentially harmful characters or numbers")
    private String author;

    @Positive(message = "number of pages cannot be less than one")
    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @JsonIgnore
    @Column(name = "created_at")
    private Instant createdAt;

    @JsonIgnore
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

}
