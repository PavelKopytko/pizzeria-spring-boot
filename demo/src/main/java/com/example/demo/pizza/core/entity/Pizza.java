package com.example.demo.pizza.core.entity;


import com.example.demo.pizza.core.entity.api.IPizza;

import java.time.LocalDateTime;

public class Pizza implements IPizza {

    private long id;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String name;
    private long size;

    public Pizza() {
    }

    public Pizza(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public Pizza(LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, long size) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.size = size;
    }

    public Pizza(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, long size) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.size = size;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
