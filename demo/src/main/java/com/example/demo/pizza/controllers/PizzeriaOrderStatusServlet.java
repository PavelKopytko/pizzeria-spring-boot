package com.example.demo.pizza.controllers;

import com.example.demo.pizza.core.dto.OrderStatusDto;
import com.example.demo.pizza.core.dto.PizzaInfoDto;
import com.example.demo.pizza.core.entity.api.IOrderStatus;
import com.example.demo.pizza.core.entity.api.IPizzaInfo;
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
@RequestMapping("/pizzeria/order-status")
public class PizzeriaOrderStatusServlet {


    private final IOrderStatusService service;

    public PizzeriaOrderStatusServlet(IOrderStatusService service) {
        this.service = service;
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    protected ResponseEntity<IOrderStatus> doGet(@PathVariable long id) {

        try {
//            IOrderStatus orderStatus = service.read(id);
//            if (orderStatus == null) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
                return ResponseEntity.ok(service.read(id));
//            }

        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<List<? extends IOrderStatus>> getList() {

        try {

            return ResponseEntity.ok(service.get());

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
    protected ResponseEntity<IOrderStatus> doPost(@RequestBody OrderStatusDto orderDto) {

        try {

            IOrderStatus orderStatus = this.service.create(orderDto);
            return new ResponseEntity<IOrderStatus>(orderStatus, HttpStatus.CREATED);

        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (ValidateException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    // id - OrderStatus.id  глупо, но пока так

    @PutMapping("/{id}/dt_update/{dt_update}")
    protected ResponseEntity<IOrderStatus> doPut(@PathVariable long id,
                                               @PathVariable("dt_update") long dtUpdateRaw,
                                               @RequestBody OrderStatusDto orderStatusDto) {

        try {
            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dtUpdateRaw),
                    ZoneId.of("UTC")
            );

            return ResponseEntity.ok(this.service.update(id, dtUpdate, orderStatusDto));


        } catch (ValidateException | IllegalArgumentException /*| IOException*/ e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return null;
    }

}
