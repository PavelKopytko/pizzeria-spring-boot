package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.PizzaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPizzaInfoDao extends JpaRepository<PizzaInfo, Long> {
}
