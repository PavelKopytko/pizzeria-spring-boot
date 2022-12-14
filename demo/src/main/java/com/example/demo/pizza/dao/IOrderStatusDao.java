package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusDao extends JpaRepository<OrderStatus, Long> {

    OrderStatus findAllByTicketId(Long id);
}
