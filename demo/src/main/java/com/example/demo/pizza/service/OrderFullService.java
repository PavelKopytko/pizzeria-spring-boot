package com.example.demo.pizza.service;

import com.example.demo.pizza.core.dto.OrderDto;
import com.example.demo.pizza.core.dto.SelectedItemDto;
import com.example.demo.pizza.core.dto.TicketDto;
import com.example.demo.pizza.core.entity.Order;
import com.example.demo.pizza.core.entity.SelectedItem;
import com.example.demo.pizza.core.entity.Ticket;
import com.example.demo.pizza.core.entity.api.IOrder;
import com.example.demo.pizza.core.entity.api.ISelectedItem;
import com.example.demo.pizza.core.entity.api.ITicket;
import com.example.demo.pizza.dao.IOrderFullDao;
import com.example.demo.pizza.service.api.IMenuRowService;
import com.example.demo.pizza.service.api.IOrderFullService;
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
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class OrderFullService implements IOrderFullService {

    private final IOrderFullDao orderFullDao;
    private final ITicketService2 ticketService;
    private final IMenuRowService menuRowService;

    public OrderFullService(IOrderFullDao orderFullDao, ITicketService2 ticketService, IMenuRowService menuRowService) {
        this.orderFullDao = orderFullDao;
        this.ticketService = ticketService;
        this.menuRowService = menuRowService;
    }

    @Override
    @Transactional
    public ITicket create(OrderDto item) throws ServiceException, ValidateException, NotUniqServiceException {
        Ticket ticket;
        IOrder order;

        try {
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

            List<ISelectedItem> selectedItems = new ArrayList<>();
            for (SelectedItemDto selectedItemDto : item.getItems()) {
                ISelectedItem selectedItem = new SelectedItem(
                        localDateTime,
                        localDateTime,
                        this.menuRowService.read(selectedItemDto.getMenuRowDto()),
                        selectedItemDto.getCount()
                );
                selectedItems.add(selectedItem);
            }

            Order orderIn = new Order();
            orderIn.setDtCreate(localDateTime);
            orderIn.setDtUpdate(localDateTime);
            orderIn.setSelected(selectedItems);

//            order = this.orderFullDao.save(orderIn);

//            item.setId(order.getId());

            TicketDto ticketDto = new TicketDto();

            Ticket ticket1 = new Ticket(
                    localDateTime,
                    localDateTime,
                    "Number Spring",
                    orderIn
            );

//            ticketDto.setOrder(item);
//
//            ticketDto.setNumber("Number from OrderService with Spring");

            ticket = this.ticketService.create(ticket1);

        } catch (IllegalStateException | IllegalArgumentException | IDServiceException e) {
            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        }
        return ticket;
    }

    @Override
    public Order read(long id) throws ServiceException, IDServiceException {
        Order order;
        try {
            order = orderFullDao.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new IDServiceException("pizzaInfo is not found");
        }
        return order;
    }

    @Override
    public List<Order> get() throws ServiceException {

        List<Order> orders;
//        try {
        orders = orderFullDao.findAll();
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("Нет доступных заказов");
        }
        return orders;
    }

    @Override
    @Transactional
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {
        try {
            this.orderFullDao.delete(read(id));
        } catch (IDServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
