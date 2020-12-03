package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frugalfoodie.Adapter.RecipeAdapter;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.databinding.ActivityViewRecipesBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipes extends AppCompatActivity {
    private FFRoom db;
    private List<Recipe> recipes;
    private ActivityViewRecipesBinding activityViewRecipesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewRecipesBinding = ActivityViewRecipesBinding.inflate(getLayoutInflater());
        View view = activityViewRecipesBinding.getRoot();
        setContentView(view);

        db = FFRoom.getInstance(getApplicationContext());
        recipes = db.recipeDAO().getAllRecipes();

        Log.d("Recipe", "All recipes");
        for (Recipe recipe: recipes) {
            Log.d("Recipe", recipe.toString());
        }
        RecipeAdapter adapter = new RecipeAdapter(recipes);


        activityViewRecipesBinding.RecipeRecyclerView.setAdapter(adapter);
        activityViewRecipesBinding.RecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }


}