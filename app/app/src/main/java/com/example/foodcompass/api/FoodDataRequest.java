package com.example.foodcompass.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodcompass.foodobject.FoodObject;
import com.example.foodcompass.foodobject.Meal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodDataRequest {

    // Hier wird der API-Request ausgeführt

    private static final String REQUEST_URL = "https://de.openfoodfacts.org/cgi/search.pl?action=process&search_terms={TERM}&sort_by=unique_scans_n&page_size=5&json=true";
    private static final String ID_URL = "https://de.openfoodfacts.org/api/v0/product/{ID}.json";


    private String name;
    private final Context context;
    private String productId;
    private final int RESULT_COUNT = 5;
    Meal meal;

    public FoodDataRequest(String name, Context context, Meal meal) {
        this.name = name;
        this.context = context;
        productId = "0";
        this.meal = meal;
    }

    // Mithilfe des Volley-Frameworks werden die Informationen aus dem JSON ausgelesen, hier mithilfe der Produkt ID
    // Dies ist für das finden der Produkte mit dem QR-Code wichitg.

    public void singleRun(RequestListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "";
        if (this.productId.equals("0")) {
            url = ID_URL.replace("{ID}", name);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, response -> {
                        listener.onResult(getFoodDataFromResponse(response));
                    }, error -> {
                        Log.d("request", "error");
                    });
            queue.add(jsonObjectRequest);
        }
    }

    //Mithilfe des Volley-Frameworks werden die Informationen aus dem JSON ausgelesen, hier mithilfe eines Suchbegriffs
    // Dabei werden fünf verschiedene Produkte aufgelistet

    public void groupRequest(RequestListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = REQUEST_URL.replace("{TERM}", name);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    listener.onGroupResult(getSuggestionsFromResponse(response));
                }, error -> {
                    Log.d("request", "error2");
                });
        queue.add(jsonObjectRequest);

    }


    // Hier werden die Informationen zu einem FoodObject zusammengestellt

    private FoodObject getFoodDataFromResponse(JSONObject response) {
        try {
            String name = response.getJSONObject("product").getString("product_name");
            String nutriLetter = response.getJSONObject("product").getString("nutriscore_grade");
            String productId = response.getString("code");

            float carbs = (float) response.getJSONObject("product").getJSONObject("nutriments").getLong("carbohydrates");
            float fat = (float) response.getJSONObject("product").getJSONObject("nutriments").getLong("fat");
            float proteins = (float) response.getJSONObject("product").getJSONObject("nutriments").getLong("proteins");
            float sugars = (float) response.getJSONObject("product").getJSONObject("nutriments").getLong("sugars");

            FoodObject result = new FoodObject(name, meal, FoodObject.NutriScore.getNutriscoreForLetter(nutriLetter), productId);
            result.setCarbs(carbs);
            result.setFat(fat);
            result.setProtein(proteins);
            result.setSugar(sugars);
            return result;
        } catch (JSONException exception) {

            return new FoodObject("Fehler", Meal.BREAKFAST, FoodObject.NutriScore.E, "00");
        }
    }

    private List<String> getSuggestionsFromResponse(JSONObject response) {
        List<String> result = new ArrayList<>();

        try {
            JSONArray array = response.getJSONArray("products");
            for (int i = 0; i < array.length(); i++) {
                result.add(array.getJSONObject(i).getString("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public interface RequestListener {

        void onResult(FoodObject data);

        void onGroupResult(List<String> list);

    }
}
