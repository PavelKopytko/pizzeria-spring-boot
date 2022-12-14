package com.example.demo.pizza.service.exception;

public class NotUniqServiceException extends Exception {

    public NotUniqServiceException() {
    }

    public NotUniqServiceException(String message) {
        super(message);
    }

    public NotUniqServiceException(Exception e) {
        super(e);
    }

    public NotUniqServiceException(String message, Exception e) {
        super(message, e);
    }


}
