package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;

/**
 * Приготовленная пицца
 */
public interface IPizza {
    /**
     * Название пиццы
     * @return
     */
    String getName();

    /**
     * Размер пиццы
     * @return
     */
    long getSize();

    LocalDateTime getDtCreate();

    LocalDateTime getDtUpdate();

}
