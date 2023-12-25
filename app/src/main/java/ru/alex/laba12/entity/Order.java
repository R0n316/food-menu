package ru.alex.laba12.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private Dish dish;
    private int count;

    public Order(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
