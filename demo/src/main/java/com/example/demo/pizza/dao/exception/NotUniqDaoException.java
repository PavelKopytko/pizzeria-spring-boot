package com.example.demo.pizza.dao.exception;

public class NotUniqDaoException extends Exception {

    public NotUniqDaoException() {
    }

    public NotUniqDaoException(String message) {
        super(message);
    }

    public NotUniqDaoException(Exception e) {
        super(e);
    }

    public NotUniqDaoException(String message, Exception e) {
        super(message, e);
    }


}
