package com.payu.web.client.data.dto.request;

import com.payu.web.client.data.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {

    @NotNull(message = "book name cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-:',().]+$", message = "book name contains potentially harmful characters")
    private String name;

    @NotNull(message = "isbn cannot be null")
    private String isbnNumber;

    @NotNull(message = "publish date cannot be null")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/\\d{4}$", message = "required date format is dd/MM/yyyy")
    private String publishDate;

    @NotNull(message = "book price cannot be null")
    @PositiveOrZero(message = "price of a book cannot be negative")
    private Double price;

    @NotNull(message = "book type cannot be null")
    private BookType bookType;

    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "author contains potentially harmful characters or numbers")
    private String author;

    @Positive(message = "number of pages cannot be less than one")
    private Long numberOfPages;
}
