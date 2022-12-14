package com.example.demo.pizza.service;

import com.example.demo.pizza.core.dto.TicketDto;
import com.example.demo.pizza.core.entity.Order;
import com.example.demo.pizza.core.entity.Ticket;
import com.example.demo.pizza.dao.ITicketDao;
import com.example.demo.pizza.service.api.ITicketService;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional(readOnly = true)
public class TicketService implements ITicketService {

    private final ITicketDao ticketDao;
//    private final IOrderService orderService;

    public TicketService(ITicketDao ticketDao/*, IOrderService orderService*/) {
        this.ticketDao = ticketDao;
//        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Ticket create(TicketDto item) throws ServiceException, ValidateException, NotUniqServiceException {
        Ticket ticket;

        try {
//            IOrder order = orderService.read(item.getOrder().getId());
            Order order = new Order();
            order.setId(item.getId());
            ticket = ticketDao.save(
                    new Ticket(
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            item.getNumber(),
                            order
                    )
            );

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public Ticket read(long id) throws ServiceException, IDServiceException {
        Ticket ticket;
        try {
            ticket = ticketDao.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public List<Ticket> get() throws ServiceException {

        List<Ticket> tickets;
//        try {
        tickets = ticketDao.findAll();
        if (tickets.isEmpty()) {
            throw new IllegalArgumentException("Нет доступных заказов");
        }
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }
//        }
        return tickets;
    }

    @Override
    public Ticket update(long id, LocalDateTime dtUpdate, TicketDto item) throws ServiceException, ValidateException, NotUniqServiceException {

        return null;
    }

    @Override
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {
//        try {
//            this.ticketDao.delete(id, dtUpdate);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }

    }
}
