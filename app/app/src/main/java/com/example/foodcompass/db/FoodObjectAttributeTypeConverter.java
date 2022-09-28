package com.example.foodcompass.db;


import androidx.room.TypeConverter;

import com.example.foodcompass.foodobject.FoodObject;
import com.example.foodcompass.foodobject.Meal;

public class FoodObjectAttributeTypeConverter {

    @TypeConverter
    public static String mealToString(Meal meal) {
        return meal.toString();
    }

    @TypeConverter
    public static Meal stringToMeal(String mealString) {
        return Meal.getMeal(mealString);
    }

    @TypeConverter
    public static int nutriScoreToInt(FoodObject.NutriScore nutriScore) {
        return nutriScore.getScore();
    }

    @TypeConverter
    public static FoodObject.NutriScore intToScore(int score) {
        return FoodObject.NutriScore.getNutriscoreForScore(score);

    }
}
