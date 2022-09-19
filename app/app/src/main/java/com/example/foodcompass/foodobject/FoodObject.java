package com.example.foodcompass.foodobject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//@Entity(tableName = "foodObjects")
public class FoodObject {

    public final String Name;
    //@ColumnInfo(name ="meal")

    //@PrimaryKey
    //@NonNull
    public int id = 0;
    public String productId;
    Meal meal;
    NutriScore nutriScore;

    public FoodObject(String Name, Meal meal, NutriScore nutriScore, String productId) {
        this.Name = Name;
        this.meal = meal;
        this.nutriScore = nutriScore;
        this.productId = productId;
        id++;
    }

    public enum NutriScore {

        A(5, "a"),
        B(4, "b"),
        C(3, "c"),
        D(2, "d"),
        E(1, "e");

        private int score = 0;
        private String nutriLetter = "";

        NutriScore(int score, String nutriLetter) {
            this.score = score;
            this.nutriLetter = nutriLetter;
        }

        public static NutriScore getNutriscoreForLetter(String letter) {
            for (NutriScore nutri : NutriScore.values()) {
                if (letter == nutri.nutriLetter) {
                    return nutri;
                }
            }
            return null;

        }

    }

}
