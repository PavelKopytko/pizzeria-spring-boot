package com.example.demo.pizza.service.api;

import com.example.demo.pizza.core.entity.Ticket;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;

import java.time.LocalDateTime;
import java.util.List;

public interface ITicketService2  {

    Ticket create(Ticket item) throws ServiceException, ValidateException, NotUniqServiceException;

    Ticket read(long id) throws  ServiceException;

    List<Ticket> get() throws ServiceException;

    void delete(long id, LocalDateTime dtUpdate) throws ServiceException;

}
