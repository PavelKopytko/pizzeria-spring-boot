package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderFullDao extends JpaRepository<Order, Long> {

}
