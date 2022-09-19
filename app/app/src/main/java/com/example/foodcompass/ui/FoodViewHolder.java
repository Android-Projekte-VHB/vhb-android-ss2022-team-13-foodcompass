package com.example.foodcompass.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcompass.R;
import com.example.foodcompass.foodobject.FoodObject;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTV;


    public FoodViewHolder(@NonNull View itemView){
        super(itemView);
        nameTV = itemView.findViewById(R.id.food_name);

    }

    public void bindView(FoodObject foodObject){
        nameTV.setText(foodObject.Name);
    }





}
