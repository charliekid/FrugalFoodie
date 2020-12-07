package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frugalfoodie.Adapter.RecipeAdapter;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.RecipeDAO;
import com.example.frugalfoodie.databinding.ActivityViewRecipesBinding;

import java.util.ArrayList;
import java.util.List;

// Citation: for item_recipe xml https://singhajit.com/android-padding-vs-margin/
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
        RecipeDAO rDao = db.recipeDAO();
        //recipes = db.recipeDAO().getAllRecipes();

        // TODO: Get the intent's check ingredients list
        ArrayList<Ingredient> checkedIngredientsList = new ArrayList<>();
        for(Ingredient ingredientItem : checkedIngredientsList) {
            String checkedItem = ingredientItem.getItemName();
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

        Log.d("Recipe", "All recipes");
        for (Recipe recipe: recipes) {
            Log.d("Recipe", recipe.getRecipeName());
        }
        RecipeAdapter adapter = new RecipeAdapter(recipes);


        activityViewRecipesBinding.RecipeRecyclerView.setAdapter(adapter);
        activityViewRecipesBinding.RecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));



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