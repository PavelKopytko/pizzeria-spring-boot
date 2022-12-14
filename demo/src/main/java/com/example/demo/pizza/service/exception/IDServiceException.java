package com.example.demo.pizza.service.exception;

public class IDServiceException extends Exception {

    public IDServiceException() {
    }

    public IDServiceException(String message) {
        super(message);
    }

    public IDServiceException(Exception e) {
        super(e);
    }

    public IDServiceException(String message, Exception e) {
        super(message, e);
    }


}
