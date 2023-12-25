package ru.alex.laba12.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Dish implements Serializable {
    private String dishName;
    private String category;
    private int dishImage;
    private static final HashMap<String,Float> prices = new HashMap<>();

    static {
        prices.put("Харчо",230F);
        prices.put("Омлет с луком",154F);
        prices.put("Салат летний",50F);
        prices.put("Творог",100F);
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
        return prices.get(dishName);
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
