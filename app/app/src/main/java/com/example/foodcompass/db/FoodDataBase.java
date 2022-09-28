package com.example.foodcompass.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodcompass.foodobject.FoodObject;

@Database(entities = {FoodObject.class}, version = 1)
@TypeConverters(FoodObjectAttributeTypeConverter.class)

public abstract class FoodDataBase extends RoomDatabase {

    public abstract FoodObjectDAO foodDAO();

}
