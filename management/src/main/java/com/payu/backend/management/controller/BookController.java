package com.payu.backend.management.controller;

import com.payu.backend.management.data.dto.BaseResponse;
import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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

    @Value("${pagination.page-size}")
    private int pageSize;

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

    @PostMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addNewBook(@Valid @RequestBody Book book) {
        log.info("adding book with isbn: {} to collection", book.getIsbnNumber());
        Book newBook = bookService.addNewBookToCatalogue(book);
        return new ResponseEntity<>(new BaseResponse("successful", false, newBook), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> editExistingBook(@Valid @RequestBody Book book) {
        log.info("updating book with isbn number: {}", book.getIsbnNumber());
        Book updatedBook = bookService.updateBookInCatalogue(book);
        return new ResponseEntity<>(new BaseResponse("successful", false, updatedBook), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{isbn}")
    public ResponseEntity<Void> deleteCurrentBook(@PathVariable(name = "isbn") String isbn) {
        log.info("deleting book with isbn number: {}", isbn);
        bookService.deleteBookFromCatalogue(isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/page/{page-number}")
    public ResponseEntity<BaseResponse> getPaginatedList(@PathVariable(name = "page-number") int number) {
        log.info("retrieving paginated book list in the catalogue ...");
        Page<Book> books = bookService.getPaginatedList(number, pageSize);
        return new ResponseEntity<>(new BaseResponse("successful", false, books), HttpStatus.OK);
    }
}
