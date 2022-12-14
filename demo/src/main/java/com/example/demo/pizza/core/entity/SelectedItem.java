package com.example.demo.pizza.core.entity;

import com.example.demo.pizza.core.entity.api.IMenuRow;
import com.example.demo.pizza.core.entity.api.ISelectedItem;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "selected_item")
public class SelectedItem implements ISelectedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @OneToOne(targetEntity = MenuRow.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)//LAZY-EAGER
    @JoinColumn(name = "menu_row", referencedColumnName = "id")
    private IMenuRow menuRow;
    @Column
    private int count;

    public SelectedItem() {
    }

    public SelectedItem(LocalDateTime dtCreate, LocalDateTime dtUpdate, IMenuRow menuRow, int count) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.menuRow = menuRow;
        this.count = count;
    }



    public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, IMenuRow menuRow, int count) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.menuRow = menuRow;
        this.count = count;
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

    @Override
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public IMenuRow getMenuRow() {
        return menuRow;
    }

    public void setMenuRow(IMenuRow menuRow) {
        this.menuRow = menuRow;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
