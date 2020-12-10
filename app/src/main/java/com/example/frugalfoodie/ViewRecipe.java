package com.example.frugalfoodie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.frugalfoodie.Adapter.RecipeAdapter;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.databinding.ActivityViewRecipeBinding;

import java.util.ArrayList;

public class ViewRecipe extends AppCompatActivity {

    private FFRoom db;
    private int recipe_id;
    private static final String RECIPE_ID= "recipe_id";
    private ActivityViewRecipeBinding activityViewRecipeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewRecipeBinding = ActivityViewRecipeBinding.inflate(getLayoutInflater());
        View view = activityViewRecipeBinding.getRoot();
        setContentView(view);

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        recipe_id = intent.getIntExtra(RECIPE_ID, -1);
        db = FFRoom.getInstance(getApplicationContext());
        final Recipe recipe = db.recipeDAO().getRecipeById(recipe_id);

        activityViewRecipeBinding.titleText.setText(recipe.getRecipeName());
        activityViewRecipeBinding.ingredientsText.setText(recipe.getIngredientList());
        activityViewRecipeBinding.directionsText.setText(recipe.getDirections());
        activityViewRecipeBinding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ViewRecipe.this, LandingPage.class);
                startActivity(intent);
            }
        });


    }

    public static Intent getIntent(Context context, int recipeId) {
        Intent intent = new Intent(context, ViewRecipe.class);
        intent.putExtra(RECIPE_ID, recipeId);
        return intent;

    }


}