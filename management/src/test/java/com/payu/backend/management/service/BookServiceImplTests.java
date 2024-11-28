package com.payu.backend.management.service;

import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.exception.BookAlreadyExistsException;
import com.payu.backend.management.exception.NotFoundException;
import com.payu.backend.management.repository.BookRepository;
import com.payu.backend.management.service.impl.BookServiceImpl;
import com.payu.backend.management.utils.MockedBookData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTests {

    @Mock private BookRepository bookRepository;
    @InjectMocks private BookServiceImpl bookServiceImpl;

    @Test
    public void givenAddBookRequest_WhenAddNewBookToCatalogue_ThenReturnCreatedBook() {
        Book mockedBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.existsByIsbnNumber(Mockito.anyString())).willReturn(false);
        BDDMockito.given(bookRepository.save(Mockito.any(Book.class))).willReturn(MockedBookData.createMockDataForBook());
        bookServiceImpl.addNewBookToCatalogue(mockedBook);
        Mockito.verify(bookRepository, Mockito.times(1)).existsByIsbnNumber(Mockito.anyString());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));

    }

    @Test
    public void givenAddBookRequestWithExistingIsbn_WhenAddNewBookToCatalogue_ThenReturnException() {
        BDDMockito.given(bookRepository.existsByIsbnNumber(Mockito.anyString())).willReturn(true);
        Assertions.assertThrows(BookAlreadyExistsException.class, () -> bookServiceImpl.addNewBookToCatalogue(MockedBookData.createMockDataForBook()));
        Mockito.verify(bookRepository, Mockito.times(1)).existsByIsbnNumber(Mockito.anyString());
        Mockito.verify(bookRepository, Mockito.times(0)).save(Mockito.any(Book.class));
    }

    @Test
    public void givenPageable_WhenListAllBooksInCatalogue_ThenReturnPageable() {
        List<Book> books = new ArrayList<>();
        books.add(MockedBookData.createMockDataForBook());
        BDDMockito.given(bookServiceImpl.listAllBooksInCatalogue()).willReturn(books);

        List<Book> booksList = bookServiceImpl.listAllBooksInCatalogue();
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(1,booksList.size());
    }

    @Test
    public void givenIsbn_whenDeleteBookFromCatalogue_ThenSuccessfullyDelete() {
        Book mockBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockBook.getIsbnNumber())).willReturn(Optional.of(mockBook));
        bookServiceImpl.deleteBookFromCatalogue(mockBook.getIsbnNumber());
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(mockBook.getId());
    }

    @Test
    public void givenWrongIsbn_whenDeleteBookFromCatalogue_ThenThrowNotFoundException() {
        Book mockBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockBook.getIsbnNumber())).willReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> bookServiceImpl.deleteBookFromCatalogue(mockBook.getIsbnNumber()));
        Mockito.verify(bookRepository, Mockito.times(0)).deleteById(mockBook.getId());
    }


    @Test
    public void givenUpdateBookRequestAndIsbn_whenUpdateBookInCatalogue_Then_SuccessfullyUpdate() {
        Book mockedBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockedBook.getIsbnNumber())).willReturn(Optional.of(mockedBook));
        BDDMockito.given(bookRepository.save(mockedBook)).willReturn(mockedBook);
        Book book = bookServiceImpl.updateBookInCatalogue(mockedBook);
        Mockito.verify(bookRepository, Mockito.times(1)).findByIsbnNumber(mockedBook.getIsbnNumber());
        Mockito.verify(bookRepository, Mockito.times(1)).save(mockedBook);
        Assertions.assertEquals(book.getName(), mockedBook.getName());
        Assertions.assertEquals(book.getPrice(), mockedBook.getPrice());
        Assertions.assertEquals(book.getAuthor(), mockedBook.getAuthor());
        Assertions.assertEquals(book.getNumberOfPages(), mockedBook.getNumberOfPages());
        Assertions.assertEquals(book.getBookType(), mockedBook.getBookType());
    }

    @Test
    public void givenUpdateBookRequestAndWrongIsbn_whenUpdateBookInCatalogue_Then_SuccessfullyUpdate() {
        Book mockedBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockedBook.getIsbnNumber())).willReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> bookServiceImpl.updateBookInCatalogue(mockedBook));
        Mockito.verify(bookRepository, Mockito.times(0)).save(mockedBook);
    }

    @Test
    public void givenIsbn_WhenFindByIsbnNumber_ThenReturnBookSuccessfully() {
        Book mockedBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockedBook.getIsbnNumber())).willReturn(Optional.of(mockedBook));
        Book book = bookServiceImpl.findByIsbnNumber(mockedBook.getIsbnNumber());
        Mockito.verify(bookRepository, Mockito.times(1)).findByIsbnNumber(mockedBook.getIsbnNumber());
        Assertions.assertEquals(mockedBook.getIsbnNumber(), book.getIsbnNumber());
    }

    @Test
    public void givenWrongIsbn_WhenFindByIsbnNumber_ThenReturnNotFoundException() {
        Book mockedBook = MockedBookData.createMockDataForBook();
        BDDMockito.given(bookRepository.findByIsbnNumber(mockedBook.getIsbnNumber())).willReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> bookServiceImpl.findByIsbnNumber(mockedBook.getIsbnNumber()));
        Mockito.verify(bookRepository, Mockito.times(1)).findByIsbnNumber(Mockito.anyString());
    }

    @Test
    public void givenPageNumberAndPageSize_whenGetPaginatedList_thenReturnPage() {
        BDDMockito.given(bookRepository.findAll(Mockito.any(Pageable.class))).willReturn(Mockito.any(Page.class));
        bookServiceImpl.getPaginatedList(1, 5);
        Mockito.verify(bookRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
    }
}