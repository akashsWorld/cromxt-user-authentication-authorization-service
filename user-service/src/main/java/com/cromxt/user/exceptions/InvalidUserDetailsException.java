package com.cromxt.user.exceptions;

public class InvalidUserDetailsException extends RuntimeException{
    public InvalidUserDetailsException() {
        super();
    }

    public InvalidUserDetailsException(String message) {
        super(message);
    }

    public InvalidUserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserDetailsException(Throwable cause) {
        super(cause);
    }

    protected InvalidUserDetailsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
