package com.payu.backend.management.data.dto.request;

import com.payu.backend.management.data.enums.BookType;
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
public class UpdateBookRequest {

    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-:',().]+$", message = "book name contains potentially harmful characters")
    private String name;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/\\d{4}$", message = "required date format is dd/MM/yyyy")
    private String publishDate;

    @PositiveOrZero(message = "price of a book cannot be negative")
    private Double price;

    @NotNull(message = "book type cannot be null")
    private BookType bookType;

    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "author contains potentially harmful characters")
    private String author;

    @Positive(message = "number of pages cannot be less than one")
    private Long numberOfPages;
}
