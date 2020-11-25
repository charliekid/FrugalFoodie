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

public class ViewRecipes extends AppCompatActivity {
    private FFRoom db;
    private ArrayList<Recipe> recipes;
    private ActivityViewRecipesBinding activityViewRecipesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewRecipesBinding = ActivityViewRecipesBinding.inflate(getLayoutInflater());
        View view = activityViewRecipesBinding.getRoot();
        setContentView(view);

        db = FFRoom.getInstance(getApplicationContext());
       recipes = new ArrayList<>();

        //ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) db.recipeDAO().getAllRecipes();
        //RecipeAdapter adapter = new RecipeAdapter(allRecipes, recipes);


        //activityViewRecipesBinding.RecipeRecyclerView.setAdapter(adapter);
        //activityViewRecipesBinding.RecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }



}
