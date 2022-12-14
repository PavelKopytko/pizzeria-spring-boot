package com.example.demo.pizza.core.entity;


import com.example.demo.pizza.core.entity.api.IMenuRow;
import com.example.demo.pizza.core.entity.api.IPizzaInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_row")
public class MenuRow implements IMenuRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @ManyToOne(targetEntity = PizzaInfo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "info", referencedColumnName = "id")
    private IPizzaInfo info;

    private double price;


    public MenuRow() {
    }

    public MenuRow(LocalDateTime dtCreate, LocalDateTime dtUpdate, IPizzaInfo info, double price) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.info = info;
        this.price = price;
    }


    public MenuRow(Long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, IPizzaInfo info, double price) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.info = info;
        this.price = price;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public IPizzaInfo getInfo() {
        return info;
    }

    @Override
    public void setInfo(IPizzaInfo info) {
        this.info = info;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }


}
