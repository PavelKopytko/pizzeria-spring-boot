package com.example.demo.pizza.controllers.util.mapper;

import com.example.demo.pizza.controllers.util.mapper.serialization.LocalDateTimeDeserializer;
import com.example.demo.pizza.controllers.util.mapper.serialization.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class ObjectMapperTime {

    private final com.fasterxml.jackson.databind.ObjectMapper mapper;

    public ObjectMapperTime() {
        this.mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        customModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.registerModule(customModule);
    }
}
