package com.cromxt.user.exceptions;

public class ProfileAvatarNotFoundException extends RuntimeException{
    public ProfileAvatarNotFoundException() {
        super();
    }

    public ProfileAvatarNotFoundException(String message) {
        super(message);
    }

    public ProfileAvatarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfileAvatarNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProfileAvatarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
