package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.RecipeDAO;

import java.util.ArrayList;
import java.util.List;

public class DummyViewRecipes extends AppCompatActivity {

    private String TAG = "DUMMY_ACTIVITY_TAG";
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_view_recipes);

        RecipeDAO rDao = FFRoom.getInstance(DummyViewRecipes.this).recipeDAO();
        TextView dummyTextView = (TextView) findViewById(R.id.dummyTextView);

        //TODO: Pre filing this checkedIngredientList but should be passed in from SearchIngredients Page
        List<String> checkedIngredientList = new ArrayList<>();
        checkedIngredientList.add("Celery");

        for(String checkedItem : checkedIngredientList) {
            // TODO: We need to account if there are two words like 'rib steak'
            List<Recipe> foundRecipes = rDao.searchForRecipeByIngredient(checkedItem);
            for(Recipe aRecipe : foundRecipes) {
                recipes.add(aRecipe);
                Log.d(TAG, "Added: " + aRecipe.getRecipeName());
            }
        }
//        Log.d(TAG, "size of recipe list: " + recipes.size());
        dummyTextView.setText("recipe size " + recipes.size());
    }
}