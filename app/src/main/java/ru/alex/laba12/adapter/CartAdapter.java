package ru.alex.laba12.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.alex.laba12.R;
import ru.alex.laba12.entity.Dish;
import ru.alex.laba12.entity.Order;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private final LayoutInflater layoutInflater;
    private final List<Dish> dishes;
    private List<Order> orders;
    public CartAdapter(Context context,List<Order> orders){
        layoutInflater = LayoutInflater.from(context);
        this.orders = orders;
        dishes = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            dishes.add(orders.get(i).getDish());
        }
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cart_list_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.dishImage.setImageResource(dish.getDishImage());
        holder.dishName.setText(dish.getDishName());
        String count = orders.get(position).getCount()+" шт";
        holder.count.setText(count);
        String price = (orders.get(position).getDish().getPrice()*orders.get(position).getCount())
                +" руб";
        holder.price.setText(price);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder{
        private final ImageView dishImage;
        private final TextView dishName;
        private final TextView dishCategory;
        private final TextView count;
        private final TextView price;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            dishImage = itemView.findViewById(R.id.dish_image);
            dishName = itemView.findViewById(R.id.dish_name);
            dishCategory = itemView.findViewById(R.id.dish_category);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
        }
    }
}