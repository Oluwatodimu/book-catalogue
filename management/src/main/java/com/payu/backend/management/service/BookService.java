package com.payu.backend.management.service;

import com.payu.backend.management.data.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    /**
     * method for getting all books in the catalogue
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

    /**
     * method for getting all books (paginated) in the catalogue
     * @Parameter: page -> page number
     * @Parameter: size -> page size
     * @Returns: paginated list of books in the catalogue
     * */
    Page<Book> getPaginatedList(int page, int size);
}
