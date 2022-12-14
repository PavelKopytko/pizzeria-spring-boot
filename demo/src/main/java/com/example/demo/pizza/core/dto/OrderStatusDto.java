package com.example.demo.pizza.core.dto;

public class OrderStatusDto {

    private long ticket;
    private String stage;

    public OrderStatusDto() {
    }

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
