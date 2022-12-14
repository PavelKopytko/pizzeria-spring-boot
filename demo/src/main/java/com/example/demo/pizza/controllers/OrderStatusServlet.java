package com.example.demo.pizza.controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//CRUD controller
//IOrderSt
@WebServlet(name = "OrderStatusServlet", urlPatterns = "/order-status")
public class OrderStatusServlet extends HttpServlet {


//    public OrderStatusServlet() {
//        this.service = SelectedItemServiceSingleton.getInstance();
//        this.mapper = new ObjectMapper();
//    }

    //Read POSITION
    //1) Read item (card) need id param ticket
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
//        String queryString = req.getQueryString();
//
//        PrintWriter writer = resp.getWriter();
//
//
//        if (queryString == null) {
//
//            List<SelectedItemDto> selectedItemDtos = service.get();
//
//            writer.write(this.mapper.writeValueAsString(selectedItemDtos));
//
//        } else {
//
//            String idParam = req.getParameter(ID);
//
//            long id;
//
//            try {
//                id = Integer.parseInt(idParam);
//            } catch (NumberFormatException e) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                return;
//            }

//            SelectedItemDto selectedItemDto = service.read(id);

//            try {
//                writer.write(this.mapper.writeValueAsString(selectedItemDto));
//            } catch (IOException e) {
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            }
//        }
    }


    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType(CONTENT_TYPE);
//
//        SelectedItemDto dto;
//
//        try {
//            dto = this.mapper.readValue(req.getInputStream(), SelectedItemDto.class);
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }

//        SelectedItemDto dtoOut;

//        try {
//            dtoOut = this.service.create(dto);
//        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            return;
//        }

//        PrintWriter writer = resp.getWriter();

//        writer.write(this.mapper.writeValueAsString(dtoOut));

//        resp.setStatus(HttpServletResponse.SC_CREATED);


    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String idParam = req.getParameter(ID);
//        String updateParam = req.getParameter(DT_UPDATE);
//
//        long id;
//        long update;
//        try {
//            id = Integer.parseInt(idParam);
//            update = Integer.parseInt(updateParam);
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        service.delete(id, update);
    }
}
