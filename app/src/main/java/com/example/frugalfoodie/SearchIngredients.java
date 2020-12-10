package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
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
    private ArrayList<Ingredient> checkedIngredients;
    private ArrayList<Integer> checkedIngredientsId = new ArrayList<>();
    private ActivitySearchIngredientsBinding activitySearchIngredientsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchIngredientsBinding = ActivitySearchIngredientsBinding.inflate(getLayoutInflater());
        View view = activitySearchIngredientsBinding.getRoot();
        setContentView(view);

        db = FFRoom.getInstance(getApplicationContext());
        checkedIngredients = new ArrayList<>();


//        //TODO: Delete after we read in weeklysale.txt
//        insertIngredients();

        Log.d("ingredient", String.valueOf(db.ingredientDAO().getAllIngredients().size()));
        for (Ingredient ingredient: db.ingredientDAO().getAllIngredients()) {
            Log.d("ingredient", ingredient.toString());
        }

        ArrayList<Ingredient> allIngredients = (ArrayList<Ingredient>) db.ingredientDAO().getAllIngredients();
        IngredientAdapter adapter = new IngredientAdapter(allIngredients, checkedIngredients);


        activitySearchIngredientsBinding.ingredientRecyclerView.setAdapter(adapter);
        activitySearchIngredientsBinding.ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activitySearchIngredientsBinding.searchIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("checked ingredient", "LIST OF INGREDIENTS THAT HAVE BEEN CHECKED");
                Log.d("checked ingredient", String.valueOf(checkedIngredients.size()));
                for (Ingredient ingredient: checkedIngredients) {
                    Log.d("checked ingredient", ingredient.toString());
                }

                Log.d("checked ingredient", "ADDING INGREDIENT IDS");
                for (Ingredient ingredient: checkedIngredients) {
                    checkedIngredientsId.add(ingredient.getIngredientId());
                }
                Log.d("checked ingredient", "PRINTING INGREDIENT IDS");
                for (Integer ingredientId: checkedIngredientsId) {
                    Log.d("checked ingredient id", ingredientId.toString());
                }
                Intent intent = ViewRecipes.getIntent(getApplicationContext(), checkedIngredientsId);
                startActivity(intent);
            }
        });


//        // TODO: Delete after we read in weeklysale.txt
//        db.ingredientDAO().deleteAllIngredients();
    }

    private void insertIngredients() {
        db.ingredientDAO().insertIngredient(new Ingredient("Haas Avacados", 5.0, 4, "na"));
        db.ingredientDAO().insertIngredient(new Ingredient("Red or Green Seedless Grapes", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientC", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientD", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientE", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientF", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientG", 1.0, 1, "lb."));
        db.ingredientDAO().insertIngredient(new Ingredient("IngredientH", 1.0, 1, "lb."));
    }
}