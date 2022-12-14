package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;

/**
 * Информация о пицце
 */
public interface IPizzaInfo {

    /**
     * Название пиццы
     * @return
     */
    String getName();

    /**
     * Описание/состав пиццы
     * @return
     */
    String getDescription();

    /**
     * Итоговый размер пиццы которую приготовят
     * @return
     */
    long getSize();

    Long getId();

    void setId(Long id);

    void setName(String name);

    void setDescription(String description);

    void setSize(long size);

    LocalDateTime getDtUpdate();

    LocalDateTime getDtCreate();

    void setDtUpdate(LocalDateTime dtUpdate);
}
