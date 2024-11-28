package com.payu.backend.management.utils;


import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.data.enums.BookType;

public class MockedBookData {

    public static Book createMockDataForBook() {
        return Book.builder()
                .id(15L)
                .name("clean code")
                .isbnNumber("0123456789")
                .publishDate("25/11/2006")
                .price(20.0)
                .bookType(BookType.HARD_COVER)
                .author("robert c. martin")
                .numberOfPages(345L)
                .build();
    }
}
