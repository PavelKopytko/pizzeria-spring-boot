package com.example.demo.pizza.core.entity;

import com.example.demo.pizza.core.entity.api.IOrder;
import com.example.demo.pizza.core.entity.api.ITicket;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "ticket")
public class Ticket implements ITicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Column
    private String number;
    @OneToOne(targetEntity = Order.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_i", referencedColumnName = "id")
    private IOrder order;

    public Ticket() {
    }

    public Ticket(LocalDateTime dtCreate, LocalDateTime dtUpdate, String number) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.number = number;
    }

    public Ticket(LocalDateTime dtCreate, LocalDateTime dtUpdate, String number, IOrder order) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.number = number;
        this.order = order;
    }

    public Ticket(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String number, IOrder order) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.number = number;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public IOrder getOrder() {
        return order;
    }

    public void setOrder(IOrder order) {
        this.order = order;
    }
}
