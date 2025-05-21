package com.example.letsplay.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
    private Map<String,String> fieldErrors;


    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String error, String code, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(int status, String error, String code, String message, String path,Map<String,String> fieldErrors) {
        this(status, error, code, message, path);
        this.fieldErrors = fieldErrors;
    }

    public  static class Builder {
        private int status;
        private String error;
        private String code;
        private String message;
        private String path;
        private Map<String,String> fieldErrors;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder fieldErrors(Map<String,String> fieldErrors) {
            this.fieldErrors = fieldErrors;
            return this;
        }

        public ErrorResponse build() {
            if (fieldErrors != null && !fieldErrors.isEmpty()) {
                return new ErrorResponse(status, error, code, message, path, fieldErrors);
            }
            return new ErrorResponse(status, error, code, message, path);
        }
    }

    public static Builder builder(){
        return new Builder();
    }

}
