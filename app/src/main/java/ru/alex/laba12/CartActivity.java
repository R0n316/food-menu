package ru.alex.laba12;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.alex.laba12.adapter.CartAdapter;
import ru.alex.laba12.databinding.ActivityCartBinding;
import ru.alex.laba12.entity.Order;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private RecyclerView recyclerView;
    private List<Order> orders;
    private TextView sumOfProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        recyclerView = binding.dishList;
        sumOfProducts = binding.sumOfProducts;
        Intent intent = getIntent();
        orders = (List<Order>) intent.getSerializableExtra("orders");
        double sum = getSum();
        String sumResult = "ИТОГО: "+sum+" руб.";
        sumOfProducts.setText(sumResult);
        CartAdapter cartAdapter = new CartAdapter(this,orders);
        recyclerView.setAdapter(cartAdapter);
    }
    private double getSum(){
        double sum = 0;
        for (int i = 0; i < orders.size(); i++) {
            int count = orders.get(i).getCount();
            sum+=orders.get(i).getDish().getPrice()*count;
        }
        return sum;
    }
}