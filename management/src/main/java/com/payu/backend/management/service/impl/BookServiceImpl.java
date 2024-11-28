package com.payu.backend.management.service.impl;

import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.exception.BookAlreadyExistsException;
import com.payu.backend.management.exception.NotFoundException;
import com.payu.backend.management.repository.BookRepository;
import com.payu.backend.management.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> listAllBooksInCatalogue() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book addNewBookToCatalogue(Book book) {
        if (bookRepository.existsByIsbnNumber(book.getIsbnNumber())) {
            throw new BookAlreadyExistsException(String.format("Book with isbn %s already exists", book.getIsbnNumber()));
        }

        book.setCreatedAt(Instant.now());
        book.setLastModifiedAt(Instant.now());
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBookInCatalogue(Book book) {
        return Optional.ofNullable(bookRepository.findByIsbnNumber(book.getIsbnNumber()))
                .filter(Optional::isPresent)  // Ensure the book exists
                .map(existingBook -> {
                    Book existing = existingBook.get();
                    existing.setName(book.getName());
                    existing.setPublishDate(book.getPublishDate());
                    existing.setPrice(book.getPrice());
                    existing.setBookType(book.getBookType());
                    existing.setAuthor(book.getAuthor());
                    existing.setNumberOfPages(book.getNumberOfPages());
                    existing.setLastModifiedAt(Instant.now());
                    return bookRepository.save(existing);
                })
                .orElseThrow(() -> new NotFoundException(String.format("Book with ISBN %s does not exist", book.getIsbnNumber())));
    }

    @Override
    public void deleteBookFromCatalogue(String isbn) {
        Book existingBook = bookRepository.findByIsbnNumber(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("Book with isbn %s does not exist", isbn)));
        bookRepository.deleteById(existingBook.getId());
        log.info("book with isbn: {} has been completed.", isbn);
    }

    @Override
    public Book findByIsbnNumber(String isbn) {
        return bookRepository.findByIsbnNumber(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("book with isbn number: %s not found", isbn)));
    }

    @Override
    public Page<Book> getPaginatedList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return bookRepository.findAll(pageable);
    }
}
