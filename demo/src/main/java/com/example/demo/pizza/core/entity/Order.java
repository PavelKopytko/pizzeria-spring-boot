package com.example.demo.pizza.core.entity;

import com.example.demo.pizza.core.entity.api.IOrder;
import com.example.demo.pizza.core.entity.api.ISelectedItem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_t")
public class Order implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @OneToMany(targetEntity = SelectedItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ISelectedItem> selected;

    public Order() {
    }

    public Order(LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public Order(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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

    public List<ISelectedItem> getSelected() {
        return selected;
    }

    public void setSelected(List<ISelectedItem> selected) {
        this.selected = selected;
    }
}
