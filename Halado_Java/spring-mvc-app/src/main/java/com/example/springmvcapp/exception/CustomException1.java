package com.example.springmvcapp.exception;

public class CustomException1 extends RuntimeException {
    public CustomException1(String message) {
        super(message);
    }

    public CustomException1(String message, Throwable cause) {
        super(message, cause);
    }
}