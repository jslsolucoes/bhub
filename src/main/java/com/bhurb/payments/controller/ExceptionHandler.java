package com.bhurb.payments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public @ResponseBody BadRequestResponse badRequest(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        var errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .toList();
        return new BadRequestResponse(errors);
    }

    public record BadRequestResponse(Collection<String> messages) {

    }
}
