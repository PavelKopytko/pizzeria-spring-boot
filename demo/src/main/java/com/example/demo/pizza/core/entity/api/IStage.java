package com.example.demo.pizza.core.entity.api;

import java.time.LocalDateTime;

/**
 * Описание этапа выполнения заказа
 */
public interface IStage {
    /**
     * Описание этапа
     *
     * @return
     */
    String getDescription();

    /**
     * Когда этап был начат
     *
     * @return
     */
    LocalDateTime getDtUpdate();

    long getId();

    LocalDateTime getDtCreate();

    void setDtUpdate(LocalDateTime dtUpdate);
    void setDescription(String description);
}
