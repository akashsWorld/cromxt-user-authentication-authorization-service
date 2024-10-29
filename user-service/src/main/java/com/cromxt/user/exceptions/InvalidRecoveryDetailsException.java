package com.cromxt.user.exceptions;

public class InvalidRecoveryDetailsException extends RuntimeException{
    public InvalidRecoveryDetailsException() {
        super();
    }

    public InvalidRecoveryDetailsException(String message) {
        super(message);
    }

    public InvalidRecoveryDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRecoveryDetailsException(Throwable cause) {
        super(cause);
    }

    protected InvalidRecoveryDetailsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
