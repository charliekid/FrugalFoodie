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
        //checkedIngredientList.add("celery");
        checkedIngredientList.add("Italian meatballs");

        for(String checkedItem : checkedIngredientList) {
            // We need to account if there are two words like 'rib steak'
            if(checkedItem.contains(" ")) {
                String[] splitArray = checkedItem.split(" ");
                for(int i = 0; i < splitArray.length; i++) {
                    searchDbForRecipe(splitArray[i], rDao, recipes);
                }
            } else {
                searchDbForRecipe(checkedItem, rDao, recipes);
            }
        }
//        Log.d(TAG, "size of recipe list: " + recipes.size());
          dummyTextView.setText("\ntotal recipe size " + recipes.size());
    }

    /**
     * This method will search the DB for the recipe and insert into the a List of recipes
     * @param ingredientName - String that represents what will be searched in the DB
     * @param rDao - the DAO that we will be using to search
     * @param recipes - the list of recipes, in which the found recipes from the db will be
     *                  inserted in.
     */
    void searchDbForRecipe(String ingredientName, RecipeDAO rDao, List<Recipe> recipes) {
        List<Recipe> foundRecipes = rDao.searchForRecipeByIngredient(ingredientName);
        for(Recipe aRecipe : foundRecipes) {
            recipes.add(aRecipe);
        }
    }
}