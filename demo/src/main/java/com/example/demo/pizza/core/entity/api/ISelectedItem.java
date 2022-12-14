package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;

/**
 * Выбор покупателя
 */
public interface ISelectedItem {
    /**
     * Выбранное из меню
     * @return
     */
    IMenuRow getMenuRow();

    /**
     * Количество выбранного
     * @return
     */
    int getCount();

    long getId();

    void setId(long id);

    LocalDateTime getDtCreate();

    LocalDateTime getDtUpdate();


}
