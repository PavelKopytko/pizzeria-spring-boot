package com.example.demo.pizza.service;


import com.example.demo.pizza.core.dto.OrderStatusDto;
import com.example.demo.pizza.core.entity.OrderStatus;
import com.example.demo.pizza.core.entity.Stage;
import com.example.demo.pizza.core.entity.Ticket;
import com.example.demo.pizza.core.entity.api.IStage;
import com.example.demo.pizza.core.entity.api.ITicket;
import com.example.demo.pizza.dao.IOrderStatusDao;
import com.example.demo.pizza.service.api.IOrderStatusService;
import com.example.demo.pizza.service.api.ITicketService2;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderStatusService implements IOrderStatusService {

    private IOrderStatusDao dao;
    private ITicketService2 ticketService2;

    public OrderStatusService(IOrderStatusDao dao, ITicketService2 ticketService2) {
        this.dao = dao;
        this.ticketService2 = ticketService2;
    }

    @Override
    @Transactional
    public OrderStatus create(OrderStatusDto item) throws ServiceException, ValidateException, NotUniqServiceException {

        OrderStatus statusOut;

        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

        Ticket ticket = this.ticketService2.read(item.getTicket());

        List<IStage> stages = new ArrayList<>();
        stages.add(new Stage(
                        localDateTime,
                        localDateTime,
                        item.getStage()
                )
        );

        OrderStatus status = new OrderStatus(
                localDateTime,
                localDateTime,
                ticket,
                false,
                stages
        );

        statusOut = this.dao.save(status);


        //        } catch (IllegalStateException | IllegalArgumentException e) {
//            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
//        }

        return statusOut;
    }

    @Override
    public OrderStatus read(long id) throws IDServiceException, ServiceException {
        OrderStatus orderStatus = null;

        ITicket ticket = ticketService2.read(id);

        orderStatus = dao.findAllByTicketId(ticket.getId());

        List<IStage> stages = orderStatus.getHistory();
        orderStatus.setHistory(stages);

//        if (orderStatus == null) {
//
//            OrderStatusDto status = new OrderStatusDto();
//            status.setTicket(id);
//            status.setStage("Start");
//
//            try {
//                orderStatus = create(status);
//            } catch (ValidateException ex) {
//                throw new RuntimeException(ex);
//            } catch (NotUniqServiceException ex) {
//                throw new RuntimeException(ex);
//            }
//        }

        return orderStatus;
    }

    @Override
    public List<OrderStatus> get() throws ServiceException {

        List<OrderStatus> orderStatuses;

        orderStatuses = dao.findAll();
        if (orderStatuses.isEmpty()) {
            throw new IllegalArgumentException("Нет доступных pizzaInfo");
        }
        return orderStatuses;
    }

    @Override
    @Transactional
    public OrderStatus update(long id, LocalDateTime dtUpdate, OrderStatusDto item) throws ValidateException, ServiceException, NotUniqServiceException {
        OrderStatus readed;
        OrderStatus orderStatus;

        readed = dao.getReferenceById(id);

        if (readed == null) {
            throw new IllegalArgumentException("Инфо не найдено");
        }

        if (!readed.getDtUpdate().isEqual(dtUpdate)) {
            throw new IllegalArgumentException("К сожалению инфо уже было отредактировано кем-то другим");
        }

        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

        List<IStage> stages = readed.getHistory();

        Stage newStage = new Stage(
                time,
                time,
                item.getStage()
        );
        stages.add(newStage);

        if (item.getStage().equalsIgnoreCase("completed")) {
            readed.setDone(true);
        }

        readed.setDtUpdate(time);
        readed.setHistory(stages);

        orderStatus = dao.save(readed);

        return orderStatus;
    }

    @Override
    @Transactional
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {

        try {
            dao.delete(read(id));
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
