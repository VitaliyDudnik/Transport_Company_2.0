package com.example.transport_company.exception;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException() {
        super("Wrong email /or password input");
    }

    public ResourceConflictException(String message) {
        super(message);
    }
}
