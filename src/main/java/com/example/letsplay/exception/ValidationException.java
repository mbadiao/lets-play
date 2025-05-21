package com.example.letsplay.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends  BaseExeption {
    private static final String DEFAULT_CODE = "VALIDATION_ERROR";

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST, DEFAULT_CODE);
    }
}
