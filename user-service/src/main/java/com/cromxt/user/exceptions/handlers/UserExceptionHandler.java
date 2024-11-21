package com.cromxt.user.exceptions.handlers;

import com.cromxt.user.dtos.responses.ErrorResponse;
import com.cromxt.user.exceptions.InvalidRecoveryDetailsException;
import com.cromxt.user.exceptions.InvalidUserDetailsException;
import com.cromxt.user.exceptions.UserAlreadyExistsException;
import com.cromxt.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundExceptionHandler(UserNotFoundException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }
    @ExceptionHandler(InvalidUserDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidUserDetailsExceptionHandler(InvalidUserDetailsException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }
    @ExceptionHandler(InvalidRecoveryDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidRecoveryDetailsExceptionHandler(InvalidRecoveryDetailsException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }

}
