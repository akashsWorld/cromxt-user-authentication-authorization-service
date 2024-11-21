package com.cromxt.user.exceptions.handlers;

import com.cromxt.user.dtos.responses.ErrorResponse;
import com.cromxt.user.exceptions.InvalidImageFormat;
import com.cromxt.user.exceptions.ProfileAvatarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileExceptionHandlers {

    @ExceptionHandler(InvalidImageFormat.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse fileExceptionHandler(InvalidImageFormat e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }
    @ExceptionHandler(ProfileAvatarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse profileAvatarNotFoundExceptionHandler(ProfileAvatarNotFoundException e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getName());
    }
}
