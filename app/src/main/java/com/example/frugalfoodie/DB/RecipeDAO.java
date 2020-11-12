package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecipeDAO {

    @Insert
    void addRecipe(Recipe recipe);

}
