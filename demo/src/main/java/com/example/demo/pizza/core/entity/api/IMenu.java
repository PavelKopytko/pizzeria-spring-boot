package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Меню
 */
public interface IMenu {

    Long getId();

    LocalDateTime getDtCreate();

    void setDtCreate(LocalDateTime dtCreate);

    LocalDateTime getDtUpdate();

    void setDtUpdate(LocalDateTime dtUpdate);

    /**
     * Получить название меню
     *
     * @return
     */
    String getName();

    void setName(String name);

    /**
     * Доступные к заказу пункты
     *
     * @return пункты которые можно заказать
     */
    List<IMenuRow> getItems();


    /**
     * Меню доступно к использованию для заказа?
     *
     * @return
     */
    boolean isEnable();

    void setEnable(boolean enable);

    void setItems(List<IMenuRow> items);
}
