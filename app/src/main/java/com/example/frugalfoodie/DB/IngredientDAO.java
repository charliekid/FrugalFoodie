package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDAO {
    /**
     * Insert an user into the database
     * @param anIngredient - An ingredient item with sales information
     */
    @Insert
    void insertIngredient(Ingredient anIngredient);

    @Delete
    void deleteIngredient(Ingredient anIngredient);

    @Query("SELECT * FROM " + FFRoom.INGREDIENT_TABLE + " WHERE itemName = :ingredientName")
    Ingredient getIngredient(String ingredientName);

    @Query("SELECT * FROM " + FFRoom.INGREDIENT_TABLE)
    List<Ingredient> getAllIngredients();

    @Query("DELETE FROM ingredientTable")
    void deleteAllIngredients();
}
