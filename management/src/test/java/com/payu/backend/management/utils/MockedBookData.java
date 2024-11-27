package com.payu.backend.management.utils;

import com.payu.backend.management.data.dto.request.AddBookRequest;
import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.data.enums.BookType;

import java.time.LocalDate;

public class MockedBookData {

    public static AddBookRequest createMockDataForAddingNewBook() {
        return AddBookRequest.builder()
                .name("clean code")
                .isbnNumber("0123456789")
                .publishDate("25/11/2005")
                .price(20.0)
                .bookType(BookType.HARD_COVER)
                .author("robert c. martin")
                .numberOfPages(345L)
                .build();
    }

    public static UpdateBookRequest createMockDataForUpdatingBook() {
        return UpdateBookRequest.builder()
                .name("clean code v2")
                .publishDate("25/11/2005")
                .price(40.0)
                .bookType(BookType.SOFT_COVER)
                .author("robert c. martin jr")
                .numberOfPages(350L)
                .build();
    }

    public static Book createMockDataForBook() {
        return Book.builder()
                .id(15L)
                .name("clean code")
                .isbnNumber("0123456789")
                .publishDate(LocalDate.now())
                .price(20.0)
                .bookType(BookType.HARD_COVER)
                .author("robert c. martin")
                .numberOfPages(345L)
                .build();
    }
}
