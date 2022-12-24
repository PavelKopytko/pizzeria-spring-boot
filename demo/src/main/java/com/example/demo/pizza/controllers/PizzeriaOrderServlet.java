package com.example.demo.pizza.controllers;

import com.example.demo.pizza.core.dto.OrderDto;
import com.example.demo.pizza.core.entity.api.IOrder;
import com.example.demo.pizza.core.entity.api.ITicket;
import com.example.demo.pizza.service.api.IOrderFullService;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CRUD controller
//IMenuRow

@RestController
@RequestMapping("/pizzeria/order")
public class PizzeriaOrderServlet {


    private final IOrderFullService orderService;


    public PizzeriaOrderServlet(IOrderFullService orderService) {
        this.orderService = orderService;
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    protected ResponseEntity<IOrder> doGet(@PathVariable long id) {

        try {

            return ResponseEntity.ok(orderService.read(id));
        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<List<? extends IOrder>> getList() {

        try {

            return ResponseEntity.ok(orderService.get());
        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }

    //CREATE POSITION
    //body json
    @PostMapping
    protected ResponseEntity<ITicket> doPost(@RequestBody OrderDto orderDto) {

        try {

            ITicket ticket = this.orderService.create(orderDto);
            return new ResponseEntity<ITicket>(ticket, HttpStatus.CREATED);

        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (ValidateException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

}
