package com.cromxt.user.exceptions;

public class UnauthorizedAccessEndpointException extends RuntimeException{
    public UnauthorizedAccessEndpointException() {
        super();
    }

    public UnauthorizedAccessEndpointException(String message) {
        super(message);
    }

    public UnauthorizedAccessEndpointException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedAccessEndpointException(Throwable cause) {
        super(cause);
    }

    protected UnauthorizedAccessEndpointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
