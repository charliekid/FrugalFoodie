package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;

public class SearchIngredients extends AppCompatActivity {

    private FFRoom db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FFRoom.getInstance(getApplicationContext());
        setContentView(R.layout.activity_search_ingredients);

        db.loadData(getApplicationContext());

        Log.d("ingredient", String.valueOf(db.ingredientDAO().getAllIngredients().size()));
        for (Ingredient ingredient: db.ingredientDAO().getAllIngredients()) {
            Log.d("ingredient", ingredient.toString());
        }
    }
}