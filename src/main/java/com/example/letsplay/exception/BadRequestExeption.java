package com.example.letsplay.exception;

import org.springframework.http.HttpStatus;

public class BadRequestExeption extends BaseExeption {
    private static final String DEFAULT_CODE = "BAD REQUEST";

    public BadRequestExeption(String resourceName,String fieldName,Object fieldValue) {
        super( String.format("%s mauvaise requet avec %s : '%s'", resourceName, fieldName, fieldValue),
                HttpStatus.BAD_REQUEST,
                DEFAULT_CODE
        );
    }
}
