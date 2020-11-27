package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void addRecipe(Recipe recipe);

    //@Query("select * from recipeTable where ingredientList LIKE'%:ingredientName% ")
    //List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipeTable WHERE recipeId =:recipeId")
    Recipe getRecipeById(int recipeId);
}