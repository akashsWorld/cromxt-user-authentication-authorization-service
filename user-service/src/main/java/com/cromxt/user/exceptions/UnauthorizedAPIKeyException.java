package com.cromxt.user.exceptions;


public class UnauthorizedAPIKeyException extends RuntimeException{

    public UnauthorizedAPIKeyException() {
        super();
    }

    public UnauthorizedAPIKeyException(String message) {
        super(message);
    }

    public UnauthorizedAPIKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedAPIKeyException(Throwable cause) {
        super(cause);
    }

    protected UnauthorizedAPIKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
