package com.example.demo.pizza.core.entity;

import com.example.demo.pizza.core.entity.api.IStage;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stage")
public class Stage implements IStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    private String description;

    public Stage() {
    }

    public Stage(LocalDateTime dtCreate, LocalDateTime dtUpdate, String description) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.description = description;
    }

    public Stage(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String description) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
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

    @Override
    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
