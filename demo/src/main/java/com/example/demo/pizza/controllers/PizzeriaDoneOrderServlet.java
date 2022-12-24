package com.example.demo.pizza.controllers;

import com.example.demo.pizza.core.dto.OrderStatusDto;
import com.example.demo.pizza.core.entity.api.IDoneOrder;
import com.example.demo.pizza.core.entity.api.IOrderStatus;
import com.example.demo.pizza.service.api.IDoneOrderService;
import com.example.demo.pizza.service.api.IOrderStatusService;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

//CRUD controller
//IMenuRow

@RestController
@RequestMapping("/pizzeria/done-order")
public class PizzeriaDoneOrderServlet {


    private final IDoneOrderService service;

    public PizzeriaDoneOrderServlet(IDoneOrderService service) {
        this.service = service;
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    protected ResponseEntity<IDoneOrder> doGet(@PathVariable long id) {

        try {

            return ResponseEntity.ok(service.read(id));

        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
