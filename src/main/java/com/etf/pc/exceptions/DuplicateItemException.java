package com.etf.pc.exceptions;

public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(final String message) {
        super(message);
    }
}
