package org.chapeullah.exception;

public class InvalidPostIdException extends RuntimeException {
    public InvalidPostIdException(String message) {
        super(message);
    }
}
