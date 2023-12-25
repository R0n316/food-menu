package ru.alex.laba12.service;

import java.util.ArrayList;
import java.util.List;

import ru.alex.laba12.entity.Dish;
import ru.alex.laba12.entity.Order;

public class OrderService{
    public static List<Order> orders = new ArrayList<>();


    public OrderService(List<Dish> dishes){
        for(int i = 0; i< dishes.size(); i++){
            orders.add(new Order(dishes.get(i),1));
        }
    }

    public void addOrder(Dish dish,int count){
        if(count == 1){
            orders.add(new Order(dish,count));
        } else{
            orders.get(getIndex(dish.getDishName())).setCount(count);
        }
    }
    public void removeOrder(Dish dish,int count){
        if(count == 0){
            orders.remove(getIndex(dish.getDishName()));
        }
        else if(count > 0){
            orders.get(getIndex(dish.getDishName())).setCount(count);
        }
    }
    public int getIndex(String dishName){
        int index = -1;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getDish().getDishName().equals(dishName)){
                return i;
            }
        }
        return index;
    }
    public static List<Order> getOrders() {
        return orders;
    }

    public interface DishActionListener {
        void addOrder(Dish dish, int count);
        void removeOrder(Dish dish, int count);
    }
}
