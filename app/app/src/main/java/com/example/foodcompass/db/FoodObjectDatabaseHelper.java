package com.example.foodcompass.db;

import android.content.Context;

import androidx.room.Room;

import com.example.foodcompass.foodobject.FoodObject;

public class FoodObjectDatabaseHelper {

    private static final String DATABASE_NAME="tracking-db";
    private final Context context;
    private FoodDataBase db;

    public FoodObjectDatabaseHelper(Context context){
        this.context = context;
        //db = Room.databaseBuilder(context, FoodDataBase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public void addOrUpdate(FoodObject food){
        //FoodObject foods = db.foodsDao().getFoodObject(food.Name);
        //if(foods == null){
        //    db.foodsDao().insertFoodObject(food);
        //} else{
            //db.foodsDao().updateFoodObject(food);
        //}
    }
}
