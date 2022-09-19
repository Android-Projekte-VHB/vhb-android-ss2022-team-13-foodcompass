package com.example.foodcompass;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    /*HalfGauge tacho;
    com.ekn.gruzer.gaugelibrary.Range rangeOne, rangeTwo, rangeThree;
    int setGraphic;*/
    FloatingActionButton btn_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_food_activity);

        btn_scan = findViewById(R.id.scannerButton);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LunchAddActivity.class);
                startActivity(intent);
            }
        });


        //Tacho anzeige hier werden die Bereiche festgelegt (Farbe, Werte, etc.)

        /*rangeOne = new com.ekn.gruzer.gaugelibrary.Range();
        rangeTwo = new com.ekn.gruzer.gaugelibrary.Range();
        rangeThree = new com.ekn.gruzer.gaugelibrary.Range();

        rangeOne.setFrom(0);rangeOne.setTo(100);
        rangeTwo.setFrom(100);rangeTwo.setTo(200);
        rangeThree.setFrom(200);rangeThree.setTo(300);

        rangeOne.setColor(Color.GREEN);
        rangeTwo.setColor(Color.BLUE);
        rangeThree.setColor(Color.RED);
        tacho.setMinValue(0);
        tacho.setMaxValue(300);
        tacho.setValue(150);*/

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
}