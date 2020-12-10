package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frugalfoodie.Adapter.RecipeAdapter;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;
import com.example.frugalfoodie.DB.IngredientDAO;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.RecipeDAO;
import com.example.frugalfoodie.databinding.ActivityViewRecipesBinding;

import java.util.ArrayList;
import java.util.List;

// Citation: for item_recipe xml https://singhajit.com/android-padding-vs-margin/
public class ViewRecipes extends AppCompatActivity {
    private FFRoom db;
    private ArrayList<Integer> checkedIngredientIds;
    private List<Recipe> recipes;
    private static final String RECIPE_IDS = "recipe_ids";
    private ActivityViewRecipesBinding activityViewRecipesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewRecipesBinding = ActivityViewRecipesBinding.inflate(getLayoutInflater());
        View view = activityViewRecipesBinding.getRoot();
        setContentView(view);

//        Intent intent = getIntent();
        recipes = new ArrayList<>();
        checkedIngredientIds = (ArrayList<Integer>) getIntent().getSerializableExtra(RECIPE_IDS);
        Log.d("Ingredient", checkedIngredientIds.get(0).toString());

        db = FFRoom.getInstance(getApplicationContext());
        RecipeDAO rDao = db.recipeDAO();
        IngredientDAO iDao = db.ingredientDAO();
        //recipes = db.recipeDAO().getAllRecipes();

        // TODO: Get the intent's check ingredients list
        //ArrayList<Ingredient> checkedIngredientsList = new ArrayList<>();
        for(int ingredientItem : checkedIngredientIds) {
            Ingredient currentIngredient = iDao.getIngredientById(ingredientItem) ;
            String checkedItem = currentIngredient.getItemName();

            // We need to account if there are two words like 'rib steak'
            if(checkedItem.contains(" ")) {
                String[] splitArray = checkedItem.split(" ");
                for(int i = 0; i < splitArray.length; i++) {
                    if(splitArray[i].equals("or")) {
                        // I know this doesn't look good, but we are gonna skip these words
                    } else {
                        searchDbForRecipe(splitArray[i], rDao, recipes);
                    }
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


        Log.d("Ingredient size recipe", recipes.size() + "");
    }

    public static Intent getIntent(Context context, ArrayList<Integer> passedRecipeIds) {
        Intent intent = new Intent(context, ViewRecipes.class);
        intent.putExtra(RECIPE_IDS, passedRecipeIds);

        return intent;

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
            if(recipes.contains(aRecipe)) {
                Log.d("Ingredients already in the list", aRecipe.toString() );
                // If the recipe is already in the list, we wont add it
            } else {
                recipes.add(aRecipe);
                Log.d("Ingredient adding to recipes", aRecipe.toString());
            }
        }
    }
}