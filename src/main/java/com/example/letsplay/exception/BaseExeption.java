package com.example.letsplay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public abstract class BaseExeption extends RuntimeException {
    private final HttpStatus status;
    private final String code;

    protected BaseExeption(String message,HttpStatus status,String code) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
