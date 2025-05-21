package com.example.letsplay.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseExeption {
    private static final String DEFAULT_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String message)    {
        super(message, HttpStatus.NOT_FOUND,DEFAULT_CODE);
    }

    public ResourceNotFoundException(String resourceName,String fieldName,Object fieldValue) {
        super(
                String.format("%s introuvable avec %s : '%s'", resourceName, fieldName, fieldValue),
                HttpStatus.NOT_FOUND,
                DEFAULT_CODE
        );
    }
}
