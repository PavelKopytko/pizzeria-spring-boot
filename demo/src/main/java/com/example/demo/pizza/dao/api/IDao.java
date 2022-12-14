package com.example.demo.pizza.dao.api;

import com.example.demo.pizza.dao.exception.DaoException;
import com.example.demo.pizza.dao.exception.NotUniqDaoException;

import java.time.LocalDateTime;
import java.util.List;

public interface IDao<TYPE> {

    TYPE create(TYPE item) throws DaoException, NotUniqDaoException;

    TYPE read(long id) throws DaoException;

    List<TYPE> get() throws DaoException;

    TYPE update(long id, LocalDateTime dtUpdate, TYPE item) throws DaoException, NotUniqDaoException;

    void delete(long id, LocalDateTime dtUpdate) throws DaoException;

}
