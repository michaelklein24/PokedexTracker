package com.klein.pokedextracker.auth.controller;

import com.klein.pokedextracker.auth.exception.UsernameAlreadyExistsException;
import com.klein.pokedextracker.core.dto.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(10)
public class AuthExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentials(BadCredentialsException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), e.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> usernameAlreadyExists(UsernameAlreadyExistsException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.toString(), e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
