package com.payu.backend.management.repository;

import com.payu.backend.management.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Boolean existsByIsbnNumber(String isbnNumber);

    Optional<Book> findByIsbnNumber(String isbnNumber);
}
