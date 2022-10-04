package com.example.foodcompass.db;

import android.content.Context;

import androidx.room.Room;

import com.example.foodcompass.foodobject.FoodObject;

public class FoodObjectDatabaseHelper {

    private static final String DATABASE_NAME = "food-db";
    private final Context context;
    private FoodDataBase db;

    public FoodObjectDatabaseHelper(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, FoodDataBase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }


    public void addObject(FoodObject food) {
        db.foodDAO().insertFoodObject(food);
    }

    public void removeObject(int id) {
        db.foodDAO().deleteFoodObjectById(id);
    }

    public void getObject(int id) {
        db.foodDAO().getFoodObject(id);
    }

    public void getObjectMean() {
        db.foodDAO().getFoodObjectsForMean();
    }
}
