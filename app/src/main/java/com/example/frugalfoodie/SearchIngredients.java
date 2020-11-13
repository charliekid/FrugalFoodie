package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frugalfoodie.Adapter.IngredientAdapter;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;
import com.example.frugalfoodie.databinding.ActivitySearchIngredientsBinding;

import java.util.ArrayList;

public class SearchIngredients extends AppCompatActivity {

    private FFRoom db;
    private ActivitySearchIngredientsBinding activitySearchIngredientsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchIngredientsBinding = ActivitySearchIngredientsBinding.inflate(getLayoutInflater());
        View view = activitySearchIngredientsBinding.getRoot();
        setContentView(view);

        db = FFRoom.getInstance(getApplicationContext());


        //TODO: Delete after we read in weeklysale.txt
        insertIngredients();

        Log.d("ingredient", String.valueOf(db.ingredientDAO().getAllIngredients().size()));
        for (Ingredient ingredient: db.ingredientDAO().getAllIngredients()) {
            Log.d("ingredient", ingredient.toString());
        }

        ArrayList<Ingredient> allIngredients = (ArrayList<Ingredient>) db.ingredientDAO().getAllIngredients();
        IngredientAdapter adapter = new IngredientAdapter(allIngredients);
        activitySearchIngredientsBinding.ingredientRecyclerView.setAdapter(adapter);
        activitySearchIngredientsBinding.ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Delete after we read in weeklysale.txt
        db.ingredientDAO().deleteAllIngredients();
    }

    private void insertIngredients() {
        db.ingredientDAO().insertIngredient(new Ingredient("Haas Avacados", 5.0, 4, "na"));
        db.ingredientDAO().insertIngredient(new Ingredient("Red or Green Seedless Grapes", 1.0, 1, "lb."));
    }
}