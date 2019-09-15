package com.home.library.controller.exception.handler;

import com.home.library.dto.ErrorDto;
import com.home.library.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ErrorDto notFoundException (NotFoundException notFoundException){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMessage(notFoundException.getNotFoundMessage());
        return errorDto;

    }

}
