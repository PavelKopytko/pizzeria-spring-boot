package com.example.demo.pizza.service.api;

import com.example.demo.pizza.core.dto.OrderStatusDto;
import com.example.demo.pizza.core.entity.DoneOrder;
import com.example.demo.pizza.core.entity.OrderStatus;
import com.example.demo.pizza.core.entity.api.IDoneOrder;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.ServiceException;

public interface IDoneOrderService {
    DoneOrder read(long id) throws IDServiceException, ServiceException;
}
