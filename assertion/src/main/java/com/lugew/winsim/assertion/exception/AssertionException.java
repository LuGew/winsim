package com.lugew.winsim.assertion.exception;

/**
 * assert exception
 *
 * @author LuGew
 * @since 2020/10/14
 */
public class AssertionException extends RuntimeException {
    public AssertionException() {
        super();
    }

    public AssertionException(String message) {
        super(message);
    }

    public AssertionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertionException(Throwable cause) {
        super(cause);
    }

    protected AssertionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
