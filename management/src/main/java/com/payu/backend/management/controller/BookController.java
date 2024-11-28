package com.payu.backend.management.controller;

import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.dto.response.BaseResponse;
import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> listAllBooks() {
        log.info("retrieving books in the catalogue ...");
        List<Book> books = bookService.listAllBooksInCatalogue();
        return new ResponseEntity<>(new BaseResponse("successful", false, books), HttpStatus.OK);
    }

    @GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> findBookByIsbnNumber(@PathVariable(name = "isbn") String isbn) {
        log.info("getting book with isbn number: {}", isbn);
        Book book = bookService.findByIsbnNumber(isbn);
        return new ResponseEntity<>(new BaseResponse("successful", false, book), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addNewBook(@Valid @RequestBody Book book) {
        log.info("adding book with isbn: {} to collection", book.getIsbnNumber());
        Book newBook = bookService.addNewBookToCatalogue(book);
        return new ResponseEntity<>(new BaseResponse("successful", false, newBook), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> editExistingBook(
            @PathVariable(name = "isbn") String isbn,
            @Valid @RequestBody UpdateBookRequest request) {

        log.info("updating book with isbn number: {}", isbn);
        Book book = bookService.updateBookInCatalogue(isbn, request);
        return new ResponseEntity<>(new BaseResponse("successful", false, book), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{isbn}")
    public ResponseEntity<Void> deleteCurrentBook(@PathVariable(name = "isbn") String isbn) {
        log.info("deleting book with isbn number: {}", isbn);
        bookService.deleteBookFromCatalogue(isbn);
        return ResponseEntity.noContent().build();
    }
}
