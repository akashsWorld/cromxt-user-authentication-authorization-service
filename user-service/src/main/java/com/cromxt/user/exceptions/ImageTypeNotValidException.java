package com.cromxt.user.exceptions;

public class ImageTypeNotValidException extends RuntimeException{
    public ImageTypeNotValidException() {
        super();
    }

    public ImageTypeNotValidException(String message) {
        super(message);
    }

    public ImageTypeNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageTypeNotValidException(Throwable cause) {
        super(cause);
    }

    protected ImageTypeNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
