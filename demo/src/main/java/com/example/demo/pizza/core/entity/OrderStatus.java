package com.example.demo.pizza.core.entity;
import com.example.demo.pizza.core.entity.api.IOrderStatus;
import com.example.demo.pizza.core.entity.api.IStage;
import com.example.demo.pizza.core.entity.api.ITicket;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "order_status")
public class OrderStatus implements IOrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @OneToOne(targetEntity = Ticket.class, fetch = FetchType.EAGER)//или здесь lazy
    @JoinColumn(name = "ticket", referencedColumnName = "id")
    private ITicket ticket;
    @Column(name = "isdone")
    private boolean isDone;
    @OneToMany(targetEntity = Stage.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)//Почему lazy
    @JoinColumn(name = "order_status", referencedColumnName = "id")
    private List<IStage> history;

    public OrderStatus() {
    }

    public OrderStatus(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, ITicket ticket, boolean isDone, List<IStage> history) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.ticket = ticket;
        this.isDone = isDone;
        this.history = history;
    }

    public OrderStatus(LocalDateTime dtCreate, LocalDateTime dtUpdate, ITicket ticket, boolean isDone, List<IStage> history) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.ticket = ticket;
        this.isDone = isDone;
        this.history = history;
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
    public ITicket getTicket() {
        return ticket;
    }

    public void setTicket(ITicket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public List<IStage> getHistory() {
        return history;
    }

    public void setHistory(List<IStage> history) {
        this.history = history;
    }
}
