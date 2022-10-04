package com.example.foodcompass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.foodcompass.db.FoodObjectDAO;
import com.example.foodcompass.db.FoodObjectDatabaseHelper;
import com.example.foodcompass.foodobject.FoodObject;
import com.example.foodcompass.foodobject.Meal;
import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn_scan;
    int healthyScore;
    SpeedView tacho;
    FoodObjectDatabaseHelper helper;

    CardView breakfastButton, lunchButton, dinnerButton, snackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        tacho = findViewById(R.id.tacho);
       // calculateCurrentNutriScore();
        displayNutriScoreOnTacho(healthyScore);


        /*btn_scan = findViewById(R.id.);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LunchAddActivity.class);
                startActivity(intent);
            }
        });*/


        /*Barcode scanner per click auf ScanBtn
        btn_scan = findViewById(R.id.scannerButton);
        btn_scan.setOnClickListener(v->{
            scanBarcode();
        });
    }

    private void scanBarcode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() !=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });

         */
    }

    private void calculateCurrentNutriScore() {
        FoodObject object = helper.getObject(0);
        Log.d("objectMean", object.toString());
    }


    private void displayNutriScoreOnTacho(int healthyScore) {
        this.healthyScore = healthyScore;
        tacho.speedTo((float) healthyScore);
    }

    private void initUi() {
        breakfastButton = findViewById(R.id.frühstück_cardView);
        lunchButton = findViewById(R.id.mittagessen_cardView);
        dinnerButton = findViewById(R.id.abendessen_cardView);
        snackButton = findViewById(R.id.snack_cardView);
        Intent i = new Intent(MainActivity.this, FoodAddActivity.class);
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("Meal", Meal.BREAKFAST.germanName);
                startActivity(i);
            }
        });
        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("Meal", Meal.LUNCH.germanName);
                startActivity(i);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("Meal", Meal.DINNER.germanName);
                startActivity(i);
            }
        });
        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("Meal", Meal.SNACK.germanName);
                startActivity(i);
            }
        });

    }
}