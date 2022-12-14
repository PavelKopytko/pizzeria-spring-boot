package com.example.demo.pizza.dao.exception;

public class ReadDaoException extends Exception {

    public ReadDaoException() {
    }
    public ReadDaoException(String message) {
        super(message);
    }

    public ReadDaoException(Exception e) {
        super(e);
    }

    public ReadDaoException(String message, Exception e) {
        super(message, e);
    }


}
