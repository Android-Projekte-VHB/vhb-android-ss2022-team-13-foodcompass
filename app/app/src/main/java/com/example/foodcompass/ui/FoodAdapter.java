package com.example.foodcompass.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcompass.R;
import com.example.foodcompass.foodobject.FoodObject;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private ArrayList<FoodObject> savedFoods;

    public FoodAdapter(){
        this.savedFoods = new ArrayList<>();
    }

    public void setEntries(ArrayList<FoodObject> list){
        savedFoods.clear();
        savedFoods.addAll(list);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_viewholder_layout,parent,false);
       return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodObject foodObject = savedFoods.get(position);
        holder.bindView(foodObject);
    }

    @Override
    public int getItemCount() {
        return savedFoods.size();
    }


}
