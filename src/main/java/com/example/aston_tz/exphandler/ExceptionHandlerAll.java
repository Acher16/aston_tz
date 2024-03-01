package com.example.aston_tz.exphandler;

import com.example.aston_tz.exception.AccountNotFoundException;
import com.example.aston_tz.exception.InvalidPinException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAll {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> exceptionAccountNotFoundException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPinException.class)
    public ResponseEntity<String> exceptionInvalidPinException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
