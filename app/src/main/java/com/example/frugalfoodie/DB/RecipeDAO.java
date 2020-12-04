package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Citations: https://stackoverflow.com/questions/44234644/android-rooms-search-in-string/44448576#44448576
@Dao
public interface RecipeDAO {

    @Insert
    void addRecipe(Recipe recipe);

//    @Query("select * from recipeTable where ingredientList LIKE '%:ingredientName%' ")
//    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipeTable")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipeTable WHERE recipeId =:recipeId")
    Recipe getRecipeById(int recipeId);

    @Query("SELECT recipeName FROM recipeTable")
    List<String> getAllRecipeTitles();

    @Query("SELECT * FROM recipeTable WHERE recipeName =:recipeName")
    Recipe getRecipeByName(String recipeName);

    @Query("DELETE FROM recipeTable WHERE recipeName =:recipeName")
    void deleteRecipeByName(String recipeName);

    @Query("SELECT * FROM recipeTable WHERE ingredientList LIKE '%' || :ingredientName || '%'")
    //@Query("select * from recipeTable where ingredientList LIKE '%:ingredientName%' ")
    List<Recipe> searchForRecipeByIngredient(String ingredientName);
}