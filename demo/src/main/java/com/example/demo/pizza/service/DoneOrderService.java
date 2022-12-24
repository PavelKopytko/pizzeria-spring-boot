package com.example.demo.pizza.service;



import com.example.demo.pizza.core.entity.*;
import com.example.demo.pizza.core.entity.api.*;
import com.example.demo.pizza.service.api.IDoneOrderService;
import com.example.demo.pizza.service.api.ITicketService2;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoneOrderService implements IDoneOrderService {

    private ITicketService2 ticketService2;

    public DoneOrderService(ITicketService2 ticketService2) {
        this.ticketService2 = ticketService2;
    }


    @Override
    public DoneOrder read(long id) throws ServiceException {
        DoneOrder doneOrder = null;

        ITicket ticket = ticketService2.read(id);

        List<IPizza> pizzas = new ArrayList<>();
        for (ISelectedItem selectedItem : ticket.getOrder().getSelected()) {
            Pizza pizza = new Pizza(
                    selectedItem.getMenuRow().getInfo().getName(),
                    selectedItem.getMenuRow().getInfo().getSize()
            );
            pizzas.add(pizza);
        }


        return new DoneOrder(ticket, pizzas);
    }


}
