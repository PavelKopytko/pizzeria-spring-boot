package com.example.demo.pizza.core.entity;

import com.example.demo.pizza.core.entity.api.IMenu;
import com.example.demo.pizza.core.entity.api.IMenuRow;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu implements IMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    private String name;
    private boolean enable;
    @OneToMany(targetEntity = MenuRow.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu", referencedColumnName = "id")
    private List<IMenuRow> items;

    public Menu() {
    }

    public Menu(Long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enable, List<IMenuRow> items) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enable = enable;
        this.items = items;
    }

    public Menu(LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enable, List<IMenuRow> items) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enable = enable;
        this.items = items;
    }

    public Menu(Long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enable) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enable = enable;
    }

    public Menu(LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enable) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enable = enable;
    }

    public Menu(Long id, LocalDateTime dtUpdate, String name, boolean enable) {
        this.id = id;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.enable = enable;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public List<IMenuRow> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }

    @Override
    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Override
    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setItems(List<IMenuRow> items) {
        this.items = items;
    }
}
