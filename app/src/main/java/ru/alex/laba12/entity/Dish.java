package ru.alex.laba12.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Dish implements Serializable {
    private String dishName;
    private String category;
    private int dishImage;
    private static final HashMap<String,Float> PRICES = new HashMap<>();

    static {
        PRICES.put("Харчо",230F);
        PRICES.put("Омлет с луком",154F);
        PRICES.put("Салат летний",50F);
        PRICES.put("Творог",100F);
    }

    public Dish(String dishName, String category, int dishImage) {
        this.dishName = dishName;
        this.category = category;
        this.dishImage = dishImage;
    }
    public String getDishName() {
        return dishName;
    }
    public float getPrice() {
        return PRICES.get(dishName);
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }
}
