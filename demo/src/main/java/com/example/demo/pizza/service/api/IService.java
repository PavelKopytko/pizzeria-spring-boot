package com.example.demo.pizza.service.api;

import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;

import java.time.LocalDateTime;
import java.util.List;

public interface IService<TYPE, DtoType> {

    TYPE create(DtoType item) throws ServiceException, ValidateException, NotUniqServiceException;

    TYPE read(long id) throws IDServiceException, ServiceException;

    List<TYPE> get() throws ServiceException;

    TYPE update(long id, LocalDateTime dtUpdate, DtoType item) throws ValidateException, ServiceException, NotUniqServiceException;

    void delete(long id, LocalDateTime dtUpdate) throws ServiceException;
}
