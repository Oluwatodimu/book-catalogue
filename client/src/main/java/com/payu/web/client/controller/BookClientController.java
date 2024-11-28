package com.payu.web.client.controller;

import com.payu.web.client.data.Book;
import com.payu.web.client.data.dto.request.AddBookRequest;
import com.payu.web.client.data.dto.request.UpdateBookRequest;
import com.payu.web.client.data.dto.response.BaseResponse;
import com.payu.web.client.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookClientController {

    private final ManagementService managementService;

    @GetMapping(path = {"/", "/view-catalogue"})
    public String listAllBooks(Model model, @ModelAttribute("message") String message) {
        List<Book> books = (List<Book>) managementService.listAllBooks().getData();
        model.addAttribute("list", books);
        model.addAttribute("message", message);
        return "ViewBookCatalogue";
    }

    @GetMapping(path = "/add-book")
    public String addNewBook(Model model) {
        model.addAttribute("request", new AddBookRequest());
        return "AddNewBook";
    }

    @PostMapping(path = "/save-book")
    public String saveNewBook(AddBookRequest request, RedirectAttributes attributes) {
        BaseResponse response = managementService.addNewBook(request);
        if (response.getError()) {
            attributes.addFlashAttribute("message", response.getMessage() != null ?
                    response.getMessage(): "could not save book");
            return "redirect:/add-book";
        }

        attributes.addFlashAttribute("message", "book successfully added to catalogue");
        return "redirect:/view-catalogue";
    }

    @GetMapping(path = "/edit-book/{isbn}")
    public String editExistingBook(@PathVariable(name = "isbn") String isbn, Model model, RedirectAttributes attributes) {
        BaseResponse response = managementService.findBookByIsbnNumber(isbn);
        if (response.getError()) {
            attributes.addFlashAttribute("message", response.getMessage() != null ?
                    response.getMessage(): "could not retrieve book");
            return "redirect:/view-catalogue";
        }

        model.addAttribute("book", response.getData());
        return "EditExistingBook";
    }

    @PostMapping(path = "/save-edited-book/{isbn}")
    public String saveEditedBook(@PathVariable(name = "isbn") String isbn, UpdateBookRequest request, RedirectAttributes attributes) {
        BaseResponse response = managementService.editExistingBook(isbn, request);
        if (response.getError()) {
            attributes.addFlashAttribute("message", response.getMessage() != null ?
                    response.getMessage(): "could not save book");
            return "redirect:/edit-book/" + isbn;
        }

        attributes.addFlashAttribute("message", "book successfully added to catalogue");
        return "redirect:/view-catalogue";
    }

    @GetMapping("/delete-book/{isbn}")
    public String deleteBook(@PathVariable(name = "isbn") String isbn, RedirectAttributes attributes) {
        Integer statusCode = managementService.deleteCurrentBook(isbn);
        if (statusCode != 204) {
            attributes.addFlashAttribute("message", "failed to remove book from catalogue");
            return "redirect:/view-catalogue";
        }

        attributes.addFlashAttribute("message", "successfully removed book from catalogue");
        return "redirect:/view-catalogue";
    }
}
