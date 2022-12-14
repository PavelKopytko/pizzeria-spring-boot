package com.example.demo.pizza.service.api;

import com.example.demo.pizza.core.dto.OrderDto;
import com.example.demo.pizza.core.entity.Order;
import com.example.demo.pizza.core.entity.api.IOrder;
import com.example.demo.pizza.core.entity.api.ITicket;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderFullService{

    ITicket create(OrderDto item) throws ServiceException, ValidateException, NotUniqServiceException;

    IOrder read(long id) throws IDServiceException, ServiceException;

    List<Order> get() throws ServiceException;

    void delete(long id, LocalDateTime dtUpdate) throws ServiceException;

}
