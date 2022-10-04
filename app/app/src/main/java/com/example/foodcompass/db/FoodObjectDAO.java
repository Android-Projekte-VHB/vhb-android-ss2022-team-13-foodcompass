package com.example.foodcompass.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodcompass.foodobject.FoodObject;

@Dao
public interface FoodObjectDAO {

    @Insert
    void insertFoodObject(FoodObject object);

    @Update
    void updateFoodObject(FoodObject object);

    @Query("DELETE FROM foodObjects WHERE id = :id")
    void deleteFoodObjectById(int id);

    // letzten 7 Einträge für Durchschnittswert
    @Query("SELECT * FROM foodObjects ORDER BY nutriScore DESC LIMIT 7")
    FoodObject getFoodObjectsForMean();

    @Query("SELECT * FROM foodObjects WHERE id= :id")
    FoodObject getFoodObject(int id);

}
