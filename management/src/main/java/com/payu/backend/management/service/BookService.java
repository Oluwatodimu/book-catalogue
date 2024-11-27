package com.payu.backend.management.service;

import com.payu.backend.management.data.dto.request.AddBookRequest;
import com.payu.backend.management.data.dto.request.UpdateBookRequest;
import com.payu.backend.management.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    /**
     * method for getting all books in the catalogue
     * @Parameter: Pageable
     * @Returns: paginated list of books in the catalogue
     * */
    Page<Book> listAllBooksInCatalogue(Pageable pageable);

    /**
     * method for updating ook in the collection
     * @Parameter: UpdateBookRequest from the API
     * @Parameter: isbn unique to the book
     * @Returns: info regarding the updated book
     * */
    Book addNewBookToCatalogue(AddBookRequest request);

    /**
     * method for updating book in the collection
     * @Parameter: UpdateBookRequest from the API
     * @Parameter: isbn unique to the book
     * @Returns: info regarding the updated book
     * */
    Book updateBookInCatalogue(String isbn, UpdateBookRequest request);

    /**
     * method for deleting book from the collection
     * @Parameter: isbn unique to the book
     * */
    void deleteBookFromCatalogue(String isbn);
}
