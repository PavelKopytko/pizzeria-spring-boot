package com.example.demo.pizza.controllers.util.mapper;

import com.example.demo.pizza.controllers.util.mapper.serialization.LocalDateTimeDeserializer;
import com.example.demo.pizza.controllers.util.mapper.serialization.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class ObjectMapperSingleton {
    private static ObjectMapperSingleton instance;

    private final ObjectMapper mapper;

    public ObjectMapperSingleton() {
        this.mapper = new ObjectMapper();

        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        customModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.registerModule(customModule);
    }

    public static ObjectMapper getInstance() {
        if(instance == null){
            synchronized (ObjectMapperSingleton.class){
                if(instance == null){
                    instance = new ObjectMapperSingleton();
                }
            }
        }
        return instance.mapper;
    }
}
