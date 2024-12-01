package com.payu.web.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String handleAllExceptions(Exception ex, Model model) {
        log.error("Exception occurred at {}: {}", LocalDateTime.now(), ex.getMessage(), ex);

        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "An unexpected error occurred");
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException iae, Model model) {
        log.warn("Invalid input: {}", iae.getMessage(), iae);

        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("error", "Invalid Request");
        model.addAttribute("message", iae.getMessage());
        return "error";
    }
}
