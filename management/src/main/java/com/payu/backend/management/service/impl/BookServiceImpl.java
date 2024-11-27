package com.payu.backend.management.service.impl;

import com.payu.backend.management.data.dto.request.AddBookRequest;
import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.exception.BookAlreadyExistsException;
import com.payu.backend.management.exception.NotFoundException;
import com.payu.backend.management.repository.BookRepository;
import com.payu.backend.management.service.BookService;
import com.payu.backend.management.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> listAllBooksInCatalogue(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Book addNewBookToCatalogue(AddBookRequest request) {
        if (bookRepository.existsByIsbnNumber(request.getIsbnNumber())) {
            throw new BookAlreadyExistsException(String.format("Book with isbn %s already exists", request.getIsbnNumber()));
        }

        Book newBook = Book.builder()
                .name(request.getName().toLowerCase(Locale.ROOT).trim())
                .isbnNumber(request.getIsbnNumber().trim())
                .publishDate(DateUtils.convertDateStringToLocalDate(request.getPublishDate()))
                .price(request.getPrice())
                .bookType(request.getBookType())
                .author(request.getAuthor().toLowerCase(Locale.ROOT).trim())
                .numberOfPages(request.getNumberOfPages())
                .createdAt(Instant.now())
                .lastModifiedAt(Instant.now())
                .build();

        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public Book updateBookInCatalogue(String isbn, UpdateBookRequest request) {
        return Optional.ofNullable(bookRepository.findByIsbnNumber(isbn))
                .filter(Optional::isPresent)
                .map(book -> updateBook(book.get(), request))
                .map(bookRepository::save)
                .orElseThrow(() -> new NotFoundException(String.format("Book with isbn %s does not exist", isbn)));
    }

    @Override
    public void deleteBookFromCatalogue(String isbn) {
        Book existingBook = bookRepository.findByIsbnNumber(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("Book with isbn %s does not exist", isbn)));
        bookRepository.deleteById(existingBook.getId());
        log.info("book with isbn: {} has been completed.", isbn);
    }

    private Book updateBook(Book existingBook, UpdateBookRequest request) {
        if (request.getName() != null) {
            existingBook.setName(request.getName());
        }

        if (request.getPublishDate() != null) {
            existingBook.setPublishDate(DateUtils.convertDateStringToLocalDate(request.getPublishDate()));
        }

        if (request.getPrice() != null) {
            existingBook.setPrice(request.getPrice());
        }

        if (request.getBookType() != null) {
            existingBook.setBookType(request.getBookType());
        }

        if (request.getAuthor() != null) {
            existingBook.setAuthor(request.getAuthor());
        }

        if (request.getNumberOfPages() != null) {
            existingBook.setNumberOfPages(request.getNumberOfPages());
        }

        existingBook.setLastModifiedAt(Instant.now());
        return existingBook;
    }

}
