package com.payu.backend.management.service;

import com.payu.backend.management.data.dto.request.AddBookRequest;
import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.entity.Book;

import java.util.List;

public interface BookService {

    /**
     * method for getting all books in the catalogue
     * @Parameter: Pageable
     * @Returns: paginated list of books in the catalogue
     * */
    List<Book> listAllBooksInCatalogue();

    /**
     * method for updating ook in the collection
     * @Parameter: UpdateBookRequest from the API
     * @Parameter: isbn unique to the book
     * @Returns: info regarding the updated book
     * */
    Book addNewBookToCatalogue(Book book);

    /**
     * method for updating book in the collection
     * @Parameter: UpdateBookRequest from the API
     * @Parameter: isbn unique to the book
     * @Returns: info regarding the updated book
     * */
    Book updateBookInCatalogue(Book book);

    /**
     * method for deleting book from the collection
     * @Parameter: isbn unique to the book
     * */
    void deleteBookFromCatalogue(String isbn);

    /**
     * method for fetching book details from the collection
     * @Parameter: isbn unique to the book
     * @Returns: book entity
     * */
    Book findByIsbnNumber(String isbn);
}
