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

    //https://de.openfoodfacts.org/cgi/search.pl?action=process&search_terms={TERM}&sort_by=unique_scans_n&page_size=5&json=true
    //https://de.openfoodfacts.org/api/v0/product/{ID}.json

    // Vorlage für HTTP-Anfrage für Essensinformationen zu einem bestimmten Stichwort
    private static final String REQUEST_URL = "https://de.openfoodfacts.org/cgi/search.pl?action=process&search_terms={TERM}&sort_by=unique_scans_n&page_size=5&json=true";
    // Vorlage für HTTP-Anfrage für eine bestimmte ProductId(wichitg für den Barcodescanner)
    private static final String ID_URL = "https://de.openfoodfacts.org/api/v0/product/{ID}.json";
    // API-Key, der bei jeder Anfrage zur Authentfizierung gegenüber der API verwendet wird


    // Name der Stadt, deren Wetterinformationen abgerufen werden sollen
    private String name;
    // Kontext der aufrufenden Activity/App für Verwendung im Volley-Framework
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


    /**
     * Die Methode führt den eigentlichen API-Request durch und liefert die so erhaltenen Informationen
     * als WeatherData-Objekt an den hier übergebenen Listener zurück. Das ist notwendig, da die Anfrage
     * parallelisiert erfolgt und die aufrufende Stelle asynchron über das Ergebnis informiert werden muss.
     */
    public void singleRun(RequestListener listener) {
        // Wir verwende Volley, um die JSON-formatierten Wetter-Daten zu erhalten (Vgl. "Mensa-App")
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


    private FoodObject getFoodDataFromResponse(JSONObject response) {
        try {
            String name = response.getJSONObject("product").getString("product_name");

            // Der Name des aktuelle gültigen Wetter-Icons versteckt sich auf einer tieferen Ebene des JSONObject
            String nutriLetter = response.getJSONObject("product").getString("nutriscore_grade");
            String productId = response.getString("code");


            return new FoodObject(name, meal, FoodObject.NutriScore.getNutriscoreForLetter(nutriLetter), productId);
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

        return  result;
    }

    /**
     * Die Methode sucht aus dem JSONObject, das als Ergebnis der API-Anfrage erhalten wurde, die
     * relevanten Informationen heraus und gibt diese, verpackt als WeatherData-Objekt, zurück (Vgl "Mensa-App").
     * <p>
     * private WeatherData getWeatherDataFromResponse(JSONObject response) {
     * try {
     * String city = response.getString("name");
     * // Der Name des aktuelle gültigen Wetter-Icons versteckt sich auf einer tieferen Ebene des JSONObject
     * String icon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
     * // Aus dem ausgelesenen Icon-Namen und der URL-Vorlage wird die direkte URL zur passenden Grafik zusammengebaut
     * String iconUrl = ICON_URL.replace("{icon id}", icon);
     * <p>
     * String temp = ""+ response.getJSONObject("main").getInt("temp");
     * String description = response.getJSONArray("weather").getJSONObject(0).getString("description");
     * return new WeatherData(city, iconUrl,description,temp);
     * } catch (JSONException exception) {
     * return new WeatherData("ERROR", "ERROR","ERROR", "ERROR");
     * }
     * }
     * <p>
     * /**
     * Mit diesem Interface geben wir vor, wie Objekte aufgebaut sein müssen, die als Empfänger der
     * Request-Ergebnisse (Observer) fungieren. Ein entsprechendes Objekt muss der "run"-Methode
     * des Request übergeben werden.
     */
    public interface RequestListener {

        void onResult(FoodObject data);

        void onGroupResult(List<String> list);

    }
}
