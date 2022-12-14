package com.example.demo.pizza.core.dto;

public class SelectedItemDto {

    private /*MenuRowDto*/ long menuRowDto;
    private int count;

    public long getMenuRowDto() {
        return menuRowDto;
    }

    public void setMenuRowDto(long menuRowDto) {
        this.menuRowDto = menuRowDto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
