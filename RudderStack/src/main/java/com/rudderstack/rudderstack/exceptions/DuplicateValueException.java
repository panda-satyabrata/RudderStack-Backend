package com.rudderstack.rudderstack.exceptions;

public class DuplicateValueException extends Exception{

    private static final long serialVersionUID = 1L;

    public DuplicateValueException(String message) {
        super(message);
    }
}
