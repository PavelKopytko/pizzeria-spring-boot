package com.example.demo.pizza.controllers.spring;


import com.example.demo.pizza.core.dto.PizzaInfoDto;
import com.example.demo.pizza.core.entity.api.IPizzaInfo;
import com.example.demo.pizza.service.api.IPizzaInfoService;
import com.example.demo.pizza.service.exception.IDServiceException;
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
//@WebServlet(name = "PizzaInfoServlet", urlPatterns = "/menu/pizza-info")
@RestController
@RequestMapping("/menu/pizza-info")
public class PizzaInfoServlet {

    private IPizzaInfoService pizzaInfoService;


    public PizzaInfoServlet(IPizzaInfoService service) {
        this.pizzaInfoService = service;
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    protected ResponseEntity<IPizzaInfo> get(@PathVariable long id) /*throws ServletException, IOException*/ {

        try {

            IPizzaInfo pizzaInfo = pizzaInfoService.read(id);

//            if (pizzaInfo == null) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
            return new ResponseEntity<>(pizzaInfo, HttpStatus.OK);


        } catch (IDServiceException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
        }
    }


    @RequestMapping(method = RequestMethod.GET) ///
    protected ResponseEntity<List<? extends IPizzaInfo>> getList() {

        try {
            return ResponseEntity.ok(pizzaInfoService.get());


        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException /*| IOException*/ e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }


    //CREATE POSITION
    //body json
    @PostMapping
    protected ResponseEntity<IPizzaInfo> doPost(@RequestBody PizzaInfoDto pizzaInfoDto) {

        try {

            IPizzaInfo created = this.pizzaInfoService.create(pizzaInfoDto);

            return new ResponseEntity<IPizzaInfo>(created, HttpStatus.CREATED);

        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (ValidateException /*| IOException*/ e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json

    @PutMapping("/{id}/dt_update/{dt_update}")
    protected ResponseEntity<IPizzaInfo> doPut(@PathVariable long id,
                                               @PathVariable("dt_update") long dtUpdateRaw,
                                               @RequestBody PizzaInfoDto pizzaInfoDto) {

        try {
            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dtUpdateRaw),
                    ZoneId.of("UTC")
            );

            return ResponseEntity.ok(this.pizzaInfoService.update(id, dtUpdate, pizzaInfoDto));


        } catch (ValidateException | IllegalArgumentException /*| IOException*/ e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return null;
    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @DeleteMapping("/{id}/dt_update/{dt_update}")
    protected void doDelete(@PathVariable long id,
                            @PathVariable("dt_update") long dtUpdateRaw) {

        try {
            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dtUpdateRaw),
                    ZoneId.of("UTC")
            );

            pizzaInfoService.delete(id, dtUpdate);

        } catch (IllegalArgumentException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

