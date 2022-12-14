package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.MenuRow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRowDao extends JpaRepository<MenuRow, Long> {
}
