package com.example.foodcompass.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcompass.R;
import com.example.foodcompass.foodobject.FoodObject;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    // Der FoodViewHolder sorgt zusammen mit dem FoodAdapter dafür, dass das Essen in der RecyclerView anzeigt und verwaltet wird

    private TextView nameTV;
    private ViewHolderListener listener;
    private ConstraintLayout constraintLayout;


    public FoodViewHolder(@NonNull View itemView,ViewHolderListener listener){
        super(itemView);
        nameTV = itemView.findViewById(R.id.food_name);
        constraintLayout = itemView.findViewById(R.id.entry_constraint);
        this.listener=listener;
    }

    public void bindView(FoodObject foodObject){
        nameTV.setText(foodObject.name);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onViewHolderClicked(getAdapterPosition());
            }
        });
    }


interface ViewHolderListener{
       void onViewHolderClicked(int position);
}


}
