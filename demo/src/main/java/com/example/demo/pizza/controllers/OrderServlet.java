package com.example.demo.pizza.controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//CRUD controller
//IMenuRow
@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

//    private IOrderService service;
//    private final ObjectMapper mapper;
//    private static final String CHARSET = "UTF-8";
//    private static final String CONTENT_TYPE = "application/json";
//    private final static String ID = "id";
//    private final static String DT_UPDATE = "update";
//
//    public OrderServlet() {
//        this.service = OrderServiceSingleton.getInstance();
//        this.mapper = ObjectMapperSingleton.getInstance();
//    }

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(CHARSET);
//        resp.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
//        String queryString = req.getQueryString();
//
//        PrintWriter writer = resp.getWriter();
//
//        try {
//
//            if (queryString == null) {
//
//                List<IOrder> items = service.get();
//                writer.write(this.mapper.writeValueAsString(items));
//
//            } else {
//
//                long id = Integer.parseInt(req.getParameter(ID));
//                IOrder item = service.read(id);
//                writer.write(this.mapper.writeValueAsString(item));
//
//            }
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        } catch (ServiceException | IOException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        }
    }


    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(CHARSET);
//        resp.setCharacterEncoding(CHARSET);
//        resp.setContentType(CONTENT_TYPE);
//
//
//        try {
//            OrderDto itemDto = this.mapper.readValue(req.getInputStream(), OrderDto.class);
//
//            IOrder item = this.service.create(itemDto);
//
//            resp.getWriter().write(this.mapper.writeValueAsString(item));
//
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//
//        } catch (NotUniqServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
//        } catch (ValidateException | IOException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setCharacterEncoding(CHARSET);
//
//        try {
//            long id = Integer.parseInt(req.getParameter(ID));
//
//            LocalDateTime dtUpdate = LocalDateTime.ofInstant(
//                    Instant.ofEpochMilli(Long.parseLong(req.getParameter(DT_UPDATE))),
//                    ZoneId.of("UTC"));
//            service.delete(id, dtUpdate);
//
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        } catch (ServiceException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        } catch (IllegalArgumentException e) {
//            resp.setStatus(HttpServletResponse.SC_CONFLICT);
//        }
    }
}
