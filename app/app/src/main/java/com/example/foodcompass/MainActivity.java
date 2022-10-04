package com.example.foodcompass;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;



import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    int setGraphic;
    FloatingActionButton btn_scan;
    CardView lunchCard;
    int nutriScore;
    int healthyScore = 20;
    SpeedView tacho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tacho = findViewById(R.id.tacho);
        displayNutriScoreOnTacho(healthyScore);

        lunchCard = findViewById(R.id.mittagessen_cardView);
        lunchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LunchAddActivity.class);
                startActivity(intent);

            }
        });

        /*btn_scan = findViewById(R.id.);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LunchAddActivity.class);
                startActivity(intent);
            }
        });*/




        private int nutriScoreCalculator(int FoodNutriScore) {
            
        }




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

    private void displayNutriScoreOnTacho (int healthyScore) {
        this.healthyScore = healthyScore;

        tacho.speedTo((float)healthyScore);
    }
}