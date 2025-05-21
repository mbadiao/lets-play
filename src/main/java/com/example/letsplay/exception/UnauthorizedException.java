package com.example.letsplay.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends  BaseExeption {

    private static final String DEFAULT_CODE = "UNAUTHORIZED";

    public UnauthorizedException(String message) {
        super(message, HttpStatus.FORBIDDEN, DEFAULT_CODE);
    }
}
