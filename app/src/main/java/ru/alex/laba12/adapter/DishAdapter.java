package ru.alex.laba12.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.alex.laba12.R;
import ru.alex.laba12.entity.Dish;
import ru.alex.laba12.service.OrderService;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> implements Filterable {
    private final LayoutInflater layoutInflater;
    private final List<Dish> dishes;
    private int count;
    private OrderService.DishActionListener dishActionListener;
    private List<Dish> dishFull;
    private Filter dishFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Dish> filteredList = new ArrayList<>();
            if(constraint==null || constraint.length() == 0){
                filteredList.addAll(dishFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Dish dish:dishes){
                    if(dish.getDishName().toLowerCase().contains(filterPattern)){
                        filteredList.add(dish);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dishes.clear();
            dishes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public DishAdapter(Context context,List<Dish> dishes, OrderService.DishActionListener dishActionListener){
        layoutInflater = LayoutInflater.from(context);
        this.dishes = dishes;
        this.dishActionListener = dishActionListener;
        dishFull = new ArrayList<>(dishes);
    }
    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.main_list_item,parent,false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.dishImage.setImageResource(dish.getDishImage());
        holder.dishName.setText(dish.getDishName());
        holder.dishCategory.setText(dish.getCategory());
        holder.addButton.setOnClickListener(v -> {
            count = Integer.parseInt(holder.count.getText().toString());
            dishActionListener.addOrder(dishes.get(holder.getAdapterPosition()),++count);
            holder.count.setText(String.valueOf(count));
        });
        holder.removeButton.setOnClickListener(v -> {
            count = Integer.parseInt(holder.count.getText().toString());
            if(count>0){
                dishActionListener.removeOrder(dishes.get(holder.getAdapterPosition()), --count);
                holder.count.setText(String.valueOf(count));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    @Override
    public Filter getFilter() {
        return dishFilter;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder{
        private final ImageView dishImage;
        private final TextView dishName;
        private final TextView dishCategory;

        private final Button removeButton;
        private final Button addButton;
        private final TextView count;
        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            dishImage = itemView.findViewById(R.id.dish_image);
            dishName = itemView.findViewById(R.id.dish_name);
            dishCategory = itemView.findViewById(R.id.dish_category);
            removeButton = itemView.findViewById(R.id.button_remove);
            addButton = itemView.findViewById(R.id.button_add);
            count = itemView.findViewById(R.id.count);
        }
    }
}