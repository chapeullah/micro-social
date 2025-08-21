package org.chapeullah.web;

import org.chapeullah.exception.DuplicateUserException;
import org.chapeullah.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicate(
            DuplicateUserException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleInvalidCredentials(
            InvalidCredentialsException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

}
