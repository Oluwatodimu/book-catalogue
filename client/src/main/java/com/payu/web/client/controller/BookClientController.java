package com.payu.web.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payu.web.client.data.Book;
import com.payu.web.client.data.dto.response.BaseResponse;
import com.payu.web.client.service.ManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookClientController {

    private final ObjectMapper objectMapper;
    private final ManagementService managementService;

    @GetMapping(path = "/")
    public String listAllBooks(Model model) {
        log.info("retrieving all books in catalogue");
        List<Book> books = (List<Book>) managementService.listAllBooks().getData();
        model.addAttribute("bookList", books);
        return "index";
    }

    @GetMapping(path = "/show-new-book-form")
    public String showNewBookForm(Model model) {
        log.info("redirecting user to new book form");
        model.addAttribute("book", new Book());
        return "new_book";
    }

    @PostMapping(path = "/save-book")
    public String saveNewBook(@ModelAttribute("book") Book book, RedirectAttributes attributes) {
        log.info("saving book with isbn: {}", book.getIsbnNumber());
        BaseResponse response = managementService.addNewBook(book);
        if (response.getError()) {
            attributes.addFlashAttribute("message", response.getMessage() != null ?
                    response.getMessage(): "could not save book");
            return "redirect:/show-new-book-form";
        }

        attributes.addFlashAttribute("message", "book successfully added to catalogue");
        return "redirect:/";
    }

    @GetMapping(path = "/show-form-for-update/{isbn}")
    public String editExistingBook(@PathVariable(name = "isbn") String isbn, Model model) {
        log.info("redirecting to page to update book with isbn number: {}", isbn);
        BaseResponse response = managementService.findBookByIsbnNumber(isbn);
        Book book = objectMapper.convertValue(response.getData(), Book.class);
        model.addAttribute("book", book);
        return "update_book";
    }

    @PostMapping(path = "/save-edited-book")
    public String saveEditedBook(@ModelAttribute("book") Book book, RedirectAttributes attributes) {
        log.info("saving updated book with isbn: {}", book.getIsbnNumber());
        BaseResponse response = managementService.editExistingBook(book);
        if (response.getError()) {
            attributes.addFlashAttribute("message", response.getMessage() != null ?
                    response.getMessage(): "could not save book");
            return "redirect:/";
        }

        attributes.addFlashAttribute("message", "book successfully updated");
        return "redirect:/";
    }
//
//    @GetMapping("/delete-book/{isbn}")
//    public String deleteBook(@PathVariable(name = "isbn") String isbn, RedirectAttributes attributes) {
//        Integer statusCode = managementService.deleteCurrentBook(isbn);
//        if (statusCode != 204) {
//            attributes.addFlashAttribute("message", "failed to remove book from catalogue");
//            return "redirect:/view-catalogue";
//        }
//
//        attributes.addFlashAttribute("message", "successfully removed book from catalogue");
//        return "redirect:/view-catalogue";
//    }
}
