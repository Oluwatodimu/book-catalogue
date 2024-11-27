package com.payu.backend.management.controller;

import com.payu.backend.management.data.dto.request.AddBookRequest;
import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.dto.response.BaseResponse;
import com.payu.backend.management.data.entity.Book;
import com.payu.backend.management.service.BookService;
import com.payu.backend.management.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> listAllBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        log.info("retrieving books in the catalogue ...");
        Pageable pageable = PaginationUtils.createSortedPageableObject(page, size);
        Page<Book> books = bookService.listAllBooksInCatalogue(pageable);
        return new ResponseEntity<>(new BaseResponse("successful", false, books), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addNewBook(@Valid @RequestBody AddBookRequest request) {
        log.info("adding book with isbn: {} to collection", request.getIsbnNumber());
        Book book = bookService.addNewBookToCatalogue(request);
        return new ResponseEntity<>(new BaseResponse("successful", false, book), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> editExistingBook(
            @PathVariable(name = "isbn") String isbn,
            @Valid @RequestBody UpdateBookRequest request) {

        log.info("updating book with isbn number: {}", isbn);
        Book book = bookService.updateBookInCatalogue(isbn, request);
        return new ResponseEntity<>(new BaseResponse("successful", false, book), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> deleteCurrentBook(@PathVariable(name = "isbn") String isbn) {
        log.info("deleting book with isbn number: {}", isbn);
        bookService.deleteBookFromCatalogue(isbn);
        return new ResponseEntity<>(new BaseResponse("successful", false, null), HttpStatus.NO_CONTENT);
    }
}
