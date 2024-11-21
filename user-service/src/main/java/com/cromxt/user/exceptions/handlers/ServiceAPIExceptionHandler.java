package com.cromxt.user.exceptions.handlers;

import com.cromxt.user.dtos.responses.ErrorResponse;
import com.cromxt.user.exceptions.UnauthorizedAPIKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceAPIExceptionHandler {

    @ExceptionHandler(UnauthorizedAPIKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUserNotFoundException(UnauthorizedAPIKeyException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }

}
