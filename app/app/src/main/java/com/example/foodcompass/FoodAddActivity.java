package com.example.foodcompass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodcompass.api.FoodDataRequest;
import com.example.foodcompass.foodobject.FoodObject;
import com.example.foodcompass.foodobject.Meal;
import com.example.foodcompass.ui.FoodAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FoodAddActivity extends AppCompatActivity implements FoodAdapter.AdapterListener, DetailDialogueFragment.DetailDialogListener {

    EditText searchBar;
    RecyclerView suggestions;
    ArrayList<FoodObject> foodList;
    FoodAdapter  foodAdapter;
    ImageView magnifyingGlass;
    FloatingActionButton fab;
    String meal;
    TextView header;
    List<FoodObject> addedFoodList;// Diese Liste muss durch die Liste in der Datenbank ersetzt werden und dann mit der Tachoanzeige verbundnen werden


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_food_activity);
        initUi();

    }

    public void initUi(){
        addedFoodList = new ArrayList<>();
        Bundle b = getIntent().getExtras();
        meal = b.getString("Meal");
        header = findViewById(R.id.textHeader);
        header.setText(meal);
        foodList = new ArrayList<>();
        searchBar = findViewById(R.id.lunchSearchbar);
        suggestions = findViewById(R.id.recyclerViewLunchId);
        magnifyingGlass = findViewById(R.id.magnifyingGlassLunch);
        foodAdapter = new FoodAdapter(this);

        suggestions.setAdapter(foodAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        suggestions.setLayoutManager(linearLayoutManager);


        magnifyingGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodList.clear();
                Log.d("test","test");
                String searchText ="";
                if(searchBar.getText().toString() != null){
                searchText = searchBar.getText().toString();}
                FoodDataRequest dataRequest = new FoodDataRequest(searchText, getApplicationContext(), Meal.getMeal(meal));
                dataRequest.groupRequest(new FoodDataRequest.RequestListener() {
                    @Override
                    public void onResult(FoodObject data) {
                        foodList.add(data);
                        foodAdapter.setEntries(foodList);
                        foodAdapter.notifyDataSetChanged();
                        Log.d("Test","test2");
                    }

                    @Override
                    public void onGroupResult(List<String> list) {
                        FoodDataRequest request;
                        for(String s: list){
                            request = new FoodDataRequest(s ,getApplicationContext(),Meal.getMeal(meal));
                            request.singleRun(this);
                        }


                    }
                });
            }
        });
    }


    @Override
    public void onElementClicked(int position, FoodObject foodObject) {
        DetailDialogueFragment dialogue = new DetailDialogueFragment();
        dialogue.setFood(foodObject);
        dialogue.show(getSupportFragmentManager(), "Detail");

    }

    @Override
    public void onObjectAdded(FoodObject object) {
        addedFoodList.add(object);
    }
}