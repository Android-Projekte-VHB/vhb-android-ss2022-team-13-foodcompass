package com.example.foodcompass.foodobject;

public enum Meal {

    // Das Enum gibt an, um welche Mahlzeit es sich handelt

    BREAKFAST("Frühstück"),
    LUNCH("Mittagessen"),
    DINNER("Abendessen"),
    SNACK("Snack");
    public String germanName;

   Meal(String germanName){
       this.germanName = germanName;
   }

   public static Meal getMeal(String germanName){
       for(Meal meal: Meal.values()){
           if(germanName.equals(meal.germanName)){
               return meal;
           }
       }
       return null;
   }

}
