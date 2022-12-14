package com.example.demo.pizza.service.exception;

public class ValidateException extends Exception {

    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Exception e) {
        super(e);
    }

    public ValidateException(String message, Exception e) {
        super(message, e);
    }


}
