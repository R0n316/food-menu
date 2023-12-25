package ru.alex.laba12;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.alex.laba12.adapter.DishAdapter;
import ru.alex.laba12.databinding.ActivityMainBinding;
import ru.alex.laba12.entity.Dish;
import ru.alex.laba12.entity.Order;
import ru.alex.laba12.service.OrderService;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<Dish> dishes;
    private RecyclerView recyclerView;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        dishes = new ArrayList<>();
        setInitialData();
        orderService = new OrderService(dishes);
        recyclerView = binding.dishList;
        DishAdapter dishAdapter = new DishAdapter(this, dishes, new OrderService.DishActionListener() {
            @Override
            public void addOrder(Dish dish, int count) {
                orderService.addOrder(dish,count);
            }
            @Override
            public void removeOrder(Dish dish, int count) {
                orderService.removeOrder(dish,count);
            }
        });
        recyclerView.setAdapter(dishAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.cart){
            Intent intent = new Intent(this,CartActivity.class);
            List<Order> orders = OrderService.getOrders();
            intent.putExtra("orders",(Serializable) getSelectedDishes(orders));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private List<Order> getSelectedDishes(List<Order> orders){
        List<Order> selectedDishes = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getCount()>0){
                selectedDishes.add(orders.get(i));
            }
        }
        return selectedDishes;
    }

    private void setInitialData(){
        dishes.add(new Dish("Харчо","Суп",R.drawable.harcho));
        dishes.add(new Dish("Омлет с луком","Завтрак",R.drawable.omlet));
        dishes.add(new Dish("Салат летний","Закуски",R.drawable.salat_letny));
        dishes.add(new Dish("Творог","Завтрак",R.drawable.tvorog));
    }
}