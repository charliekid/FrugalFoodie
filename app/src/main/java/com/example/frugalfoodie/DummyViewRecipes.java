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
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_view_recipes);

        RecipeDAO rDao = FFRoom.getInstance(DummyViewRecipes.this).recipeDAO();
        TextView dummyTextView = (TextView) findViewById(R.id.dummyTextView);
        recipes = new ArrayList<>();

        //TODO: Pre filing this checkedIngredientList but should be passed in from SearchIngredients Page
        List<String> checkedIngredientList = new ArrayList<>();
        checkedIngredientList.add("celery");

        for(String checkedItem : checkedIngredientList) {
            // TODO: We need to account if there are two words like 'rib steak'
            List<Recipe> foundRecipes = rDao.searchForRecipeByIngredient(checkedItem);
            dummyTextView.setText("\nfound recipe size " + foundRecipes.size());
//            for(Recipe aRecipe : foundRecipes) {
//                recipes.add(aRecipe);
//                //Log.d(TAG, "Added: " + aRecipe.getRecipeName());
//            }
        }
//        Log.d(TAG, "size of recipe list: " + recipes.size());
          dummyTextView.append("\ntotal recipe size " + recipes.size());
//        dummyTextView.setText("checked Ingredeint size " + checkedIngredientList.size());
    }
}