package org.chapeullah.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("user is already exists");
    }
}
