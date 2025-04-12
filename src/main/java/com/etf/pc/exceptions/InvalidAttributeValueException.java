package com.etf.pc.exceptions;

public class InvalidAttributeValueException extends RuntimeException {
    public InvalidAttributeValueException(final String message) {
        super(message);
    }
}