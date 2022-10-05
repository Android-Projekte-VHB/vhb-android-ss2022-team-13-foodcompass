package com.example.foodcompass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.foodcompass.db.FoodObjectDatabaseHelper;
import com.example.foodcompass.foodobject.FoodObject;

import java.util.Objects;

// Die Activity initalisiert das Dialog-Fenster, welches aufploppt nachdem man auf ein bestimmtes Lebensmittel in der Liste geklickt hat
public class DetailDialogueFragment extends DialogFragment {

    TextView carbs;
    TextView fat;
    TextView protein;
    TextView sugar;
    FoodObject object;
    TextView foodName;
    EditText amount;
    Button addFoodButton;
    DetailDialogListener listener;
    FoodObjectDatabaseHelper helper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DetailDialogListener) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
        selectFood();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_fragment_food_display, null);
        AlertDialog dialog = builder.setView(v).create();


        return dialog;
    }


    public void initUi() {
        fat = Objects.requireNonNull(getDialog()).findViewById(R.id.Fett);
        carbs = Objects.requireNonNull(getDialog()).findViewById(R.id.Kohlenhydrate);
        sugar = Objects.requireNonNull(getDialog()).findViewById(R.id.Zucker);
        protein = Objects.requireNonNull(getDialog()).findViewById(R.id.Eiweiß);
        foodName = Objects.requireNonNull(getDialog()).findViewById(R.id.food_name);
        amount = Objects.requireNonNull(getDialog()).findViewById(R.id.Mengen_Textfeld);
        addFoodButton = Objects.requireNonNull(getDialog()).findViewById(R.id.add_food_btn);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailDialogueFragment.this.getDialog().dismiss();
                if (!amount.getText().toString().isEmpty()) {
                    for (int i = 0; i < Integer.valueOf(amount.getText().toString()); i++) {
                        listener.onObjectAdded(object);
              //          helper = new FoodObjectDatabaseHelper(getContext());
                //        helper.addObject(object);
                  //      Log.d("test_add_food", object.name);
                    }
                }
            }
        });
    }

    public void setFood(FoodObject foodObject) {
        object = foodObject;
    }


    public void selectFood() {
        if (object != null) {
            foodName.setText(object.name);
            fat.setText("Fette: " + object.fat);
            carbs.setText("Kohlenhydrate: " + object.carbs);
            sugar.setText("Zucker: " + object.sugar);
            protein.setText("Eiweiß: " + object.protein);
        }
    }

    public interface DetailDialogListener {
        void onObjectAdded(FoodObject object);
    }

}



