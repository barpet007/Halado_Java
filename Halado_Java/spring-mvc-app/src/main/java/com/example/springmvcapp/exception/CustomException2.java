package com.example.springmvcapp.exception;

public class CustomException2 extends RuntimeException {
    public CustomException2(String message) {
        super(message);
    }

    public CustomException2(String message, Throwable cause) {
        super(message, cause);
    }
}