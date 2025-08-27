package org.chapeullah.web;

import org.chapeullah.exception.AccessDeniedException;
import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.exception.InvalidPostIdException;
import org.chapeullah.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleAccessDenied(
            AccessDeniedException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(InvalidAccessTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleInvalidAccessToken(
            InvalidAccessTokenException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(InvalidPostIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidPostId(
            InvalidPostIdException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handlePostNotFound(
        PostNotFoundException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

}
