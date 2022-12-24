package com.example.demo.pizza.controllers;

import com.example.demo.pizza.core.dto.MenuDto;
import com.example.demo.pizza.core.entity.api.IMenu;
import com.example.demo.pizza.service.api.IMenuFullService;
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

//@WebServlet(name = "PizzeriaMenuServlet", urlPatterns = "/pizzeria/menu")
@RestController
@RequestMapping("/pizzeria/menu")
public class PizzeriaMenuServlet {


    private final IMenuFullService menuFullService;
//    private final ObjectMapper mapper;


    public PizzeriaMenuServlet(IMenuFullService menu) {
        this.menuFullService = menu;
//        this.mapper = ObjectMapperSingleton.getInstance();
    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<IMenu> get(@PathVariable long id) {
//        req.setCharacterEncoding(CHARSET);
//        resp.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
//        String queryString = req.getQueryString();
//        PrintWriter writer = resp.getWriter();
//
        try {
            return ResponseEntity.ok(menuFullService.read(id));

//            if (queryString == null) {
//
//                List<IMenu> menus = menuFullService.get();
//                writer.write(this.mapper.writeValueAsString(menus));
//
//            } else {
//                long id = Integer.parseInt(req.getParameter(ID));
//                IMenu menu = menuFullService.read(id);
//                writer.write(this.mapper.writeValueAsString(menu));
//
//            }
        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().body();
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
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<? extends IMenu>> getList() {
//        req.setCharacterEncoding(CHARSET);
//        resp.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
        try {
            return ResponseEntity.ok(menuFullService.get());

//            MenuDto menuDto = this.mapper.readValue(req.getInputStream(), MenuDto.class);
//
//            IMenu menu = this.menuFullService.create(menuDto);
//
//            resp.getWriter().write(this.mapper.writeValueAsString(menu));
//
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//
//        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
//        } catch (ValidateException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<IMenu> doPost(@RequestBody MenuDto menuDto) {

        try {

            IMenu created = this.menuFullService.create(menuDto);

            return new ResponseEntity<IMenu>(created, HttpStatus.CREATED);

        } catch (NotUniqServiceException e) {
//            throw new RuntimeException(e);
        } catch (ValidateException e) {
//            throw new RuntimeException(e);
        } catch (ServiceException e) {
//            throw new RuntimeException(e);
        }
        return null;
    }


    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json
    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<IMenu> doPut(@PathVariable long id,
                                       @PathVariable("dt_update") long dtUpdateRaw,
                                       @RequestBody MenuDto menuDto) {
//        req.setCharacterEncoding(CHARSET);
//        resp.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
        try {
            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dtUpdateRaw),
                    ZoneId.of("UTC")
            );


            return ResponseEntity.ok(this.menuFullService.update(id, dtUpdate, menuDto));
//            long id = Integer.parseInt(req.getParameter(ID));
//            MenuDto menuDto = this.mapper.readValue(req.getInputStream(), MenuDto.class);
//
//            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
//                    Instant.ofEpochMilli(
//                            Long.parseLong(req.getParameter(DT_UPDATE))),
//                    ZoneId.of("UTC")
//            );
//            menuFullService.update(id, dtUpdate, menuDto);
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//
        } catch (ValidateException | IllegalArgumentException e) {
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
//        req.setCharacterEncoding(CHARSET);
//
        try {
            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dtUpdateRaw),
                    ZoneId.of("UTC")
            );

            this.menuFullService.delete(id, dtUpdate);


//        return ;//
//            long id = Integer.parseInt(req.getParameter(ID));
//
//            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
//                    Instant.ofEpochMilli(Long.parseLong(req.getParameter(DT_UPDATE))),
//                    ZoneId.of("UTC"));
//            menuFullService.delete(id, dtUpdate);
//
        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
