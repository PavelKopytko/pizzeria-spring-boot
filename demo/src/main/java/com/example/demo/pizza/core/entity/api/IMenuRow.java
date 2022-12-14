package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;

/**
 * Строчка меню
 */
public interface IMenuRow {
    /**
     * Информация о пицце
     *
     * @return
     */
    IPizzaInfo getInfo();

    void setInfo(IPizzaInfo info);

    /**
     * Стоимость пиццы
     *
     * @return
     */
    double getPrice();

    /**
     * Меню доступно к использованию для заказа?
     *
     * @return
     */
    //boolean isEnable();

    Long getId();

    void setId(Long id);

    void setPrice(double price);

    LocalDateTime getDtUpdate();

    public void setDtUpdate(LocalDateTime dtUpdate);

    LocalDateTime getDtCreate();

    void setDtCreate(LocalDateTime dtCreate);

}
