package com.example.demo.pizza.dao;

import com.example.demo.pizza.core.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuFullDao extends JpaRepository<Menu, Long> {

}
