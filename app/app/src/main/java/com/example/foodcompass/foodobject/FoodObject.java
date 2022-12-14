package com.example.foodcompass.foodobject;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

// Die Klasse stellt ein einzelnes FoodObject dar, also ein Lebensmittel, das in der App gesucht werden kann

@Entity(tableName = "foodObjects")
public class FoodObject {

    @PrimaryKey
    @NonNull
    public int id;
    public String productId;
    public final String name;
    public Meal meal;
    public NutriScore nutriScore;
    public float carbs;
    public float fat;
    public float protein;
    public float sugar;

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }


    public FoodObject(String name, Meal meal, NutriScore nutriScore, String productId) {
        this.name = name;
        this.meal = meal;
        this.nutriScore = nutriScore;
        this.productId = productId;
        id++;
        carbs = 0;
        fat = 0;
        protein = 0;
        sugar = 0;
    }


    public enum NutriScore {

        A(5, "a"),
        B(4, "b"),
        C(3, "c"),
        D(2, "d"),
        E(1, "e");

        private int score;
        private String nutriLetter;

        NutriScore(int score, String nutriLetter) {
            this.score = score;
            this.nutriLetter = nutriLetter;
        }

        public static NutriScore getNutriscoreForLetter(String letter) {
            for (NutriScore nutri : NutriScore.values()) {
                if (Objects.equals(letter, nutri.nutriLetter)) {
                    return nutri;
                }
            }
            return null;
        }

        public static NutriScore getNutriscoreForScore(int score) {
            for (NutriScore nutri : NutriScore.values()) {
                if (score == nutri.score) {
                    return nutri;
                }
            }
            return null;
        }

        public int getScore() {
            return score;
        }

        public String getNutriLetter() {
            return nutriLetter;
        }



    }

}
