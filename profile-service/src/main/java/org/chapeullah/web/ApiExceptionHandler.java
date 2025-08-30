package org.chapeullah.web;

import org.chapeullah.exception.InvalidAccessTokenException;
import org.chapeullah.exception.MissingLocationCountryException;
import org.chapeullah.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidAccessTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleInvalidAccessToken(
            InvalidAccessTokenException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(MissingLocationCountryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleMissingLocationCountry(
            MissingLocationCountryException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleUserNotFound(
            UserNotFoundException exception
    ) {
        return Map.of("message", exception.getMessage());
    }

}
