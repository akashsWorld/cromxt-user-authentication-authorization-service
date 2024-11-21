package com.cromxt.user.exceptions;

public class InvalidImageFormat extends RuntimeException{
    public InvalidImageFormat() {
        super();
    }

    public InvalidImageFormat(String message) {
        super(message);
    }

    public InvalidImageFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageFormat(Throwable cause) {
        super(cause);
    }

    protected InvalidImageFormat(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
