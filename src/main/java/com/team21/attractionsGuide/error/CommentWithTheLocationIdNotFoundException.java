package com.team21.attractionsGuide.error;

/**
 * This class is a custom exception *   will be thrown when no comment is match with the request location id
 */
public class CommentWithTheLocationIdNotFoundException extends Exception {
    /**
     * default exception methods from the exception class
     */
    public CommentWithTheLocationIdNotFoundException() {
        super();
    }

    public CommentWithTheLocationIdNotFoundException(String message) {
        super(message);
    }

    public CommentWithTheLocationIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentWithTheLocationIdNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CommentWithTheLocationIdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
