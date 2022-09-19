package com.example.foodcompass.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodcompass.foodobject.FoodObject;

import java.util.List;

//@Dao
public interface FoodObjectDAO {

   // @Insert
    void insertFoodObject(FoodObject food);

    //@Update
    void updateFoodObject(FoodObject food);

   // @Query("SELECT * FROM foodObjects WHERE Name= :Name")
    FoodObject getFoodObject(String Name);





}
