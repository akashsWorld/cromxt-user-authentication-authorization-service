package com.cromxt.user.exceptions;

public class InvalidAPIKeyException extends RuntimeException{
    public InvalidAPIKeyException() {
        super();
    }

    public InvalidAPIKeyException(String message) {
        super(message);
    }

    public InvalidAPIKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAPIKeyException(Throwable cause) {
        super(cause);
    }

    protected InvalidAPIKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
