package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketDao extends JpaRepository<Ticket, Long> {
}
