package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDAO {
    /**
     * Insert an ingredient into the database
     * @param anIngredient - An ingredient item with sales information
     */
    @Insert
    void insertIngredient(Ingredient anIngredient);

    /**
     * Delete an ingredient from the database
     * @param anIngredient- The ingredient that is being deleted
     */
    @Delete
    void deleteIngredient(Ingredient anIngredient);

    /**
     * Retrieve an ingredient from the database
     * @param ingredientName - The name of the ingredient that is being retrieved
     * @return An ingredient
     */
    @Query("SELECT * FROM " + FFRoom.INGREDIENT_TABLE + " WHERE itemName = :ingredientName")
    Ingredient getIngredient(String ingredientName);

    /**
     * Retrieve all ingredients from the database
     * @return All ingredients
     */
    @Query("SELECT * FROM " + FFRoom.INGREDIENT_TABLE)
    List<Ingredient> getAllIngredients();

    /**
     * Delete all ingredients from the database
     */
    @Query("DELETE FROM ingredientTable")
    void deleteAllIngredients();

    /**
     * Retrieve an ingredient from the database based on id
     * @param ingredientId - The id of the ingredient being retrieved
     * @return An ingredient
     */
    @Query("SELECT * FROM " + FFRoom.INGREDIENT_TABLE + " WHERE ingredientId =:ingredientId")
    Ingredient getIngredientById(int ingredientId);
}
