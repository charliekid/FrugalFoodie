package com.example.frugalfoodie;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RecipeDAOTest {
    private FFRoom db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FFRoom.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertRecipe() throws Exception {
        // insert recipe into database
        Recipe recipe = new Recipe("Recipe", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe);

        // retrieve recipe from database
        Recipe rRetrieval = db.recipeDAO().getRecipeByName("Recipe");

        // ensure the retrieved recipe's attributes are correct
        assertEquals(rRetrieval.getRecipeName(), "Recipe");
        assertEquals(rRetrieval.getIngredientList(), "Love");
        assertEquals(rRetrieval.getDirections(), "Pour love into your food");
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        // insert recipe into database
        Recipe recipe = new Recipe("Recipe2", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe);

        // delete assignment and check if assignment has been deleted
        db.recipeDAO().deleteRecipeByName("Recipe2");
        assertNull(db.recipeDAO().getRecipeByName("Recipe2"));
    }

    @Test
    public void testGetRecipeById() throws Exception {
        // insert recipe into database
        Recipe recipe = new Recipe("Recipe3", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe);
        recipe.setRecipeId(1);

        // retrieve recipe from database
        Recipe getRecipe = db.recipeDAO().getRecipeById(1);

        // ensure the retrieved recipe has valid attributes
        assertEquals(getRecipe.getRecipeName(), "Recipe3");
        assertEquals(getRecipe.getIngredientList(), "Love");
        assertEquals(getRecipe.getDirections(), "Pour love into your food");
    }

    @Test
    public void testSearchRecipeByIngredient() throws Exception {
        // insert recipe into database
        Recipe recipe = new Recipe("Recipe1", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe);
        Recipe recipe2 = new Recipe("Recipe2", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe2);

        // retrieve recipe from database
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) db.recipeDAO().searchForRecipeByIngredient("Love");

        // check if all recipes have been retrieved
        assertEquals(allRecipes.size(), 2);
        assertEquals(allRecipes.get(0).getRecipeName(), "Recipe1");
        assertEquals(allRecipes.get(1).getRecipeName(), "Recipe2");
    }

}
