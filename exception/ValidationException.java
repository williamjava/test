package com.tufire.common.exception;

/**
 * Created by it001hyl on 2019-01-24.
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 6122760335094816228L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super("ht"+message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super("ht"+message, cause);
    }
}
