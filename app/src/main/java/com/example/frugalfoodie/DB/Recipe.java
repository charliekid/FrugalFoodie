package com.example.frugalfoodie.DB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;

/**
 * Class for creating recipe object
 */
@Entity(tableName = FFRoom.RECIPE_TABLE)
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;
    private String recipeName;
    private String directions;
    private ArrayList<String> ingredientList = new ArrayList<>();

    /**
     * Constructor
     * @param recipeName - String for name
     * @param directions - String for directions
     * @param ingredientList - Arraylist for ingredient list
     */
    public Recipe(String recipeName, String directions, ArrayList<String> ingredientList) {
        this.recipeName = recipeName;
        this.directions = directions;
        this.ingredientList = ingredientList;
    }

    /**
     * Recipe Id getter
     * @return int - for user id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Recipe Id setter
     * @param recipeId - int for recipe id
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * RecipeName getter
     * @return String - for recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * RecipeName setter
     * @param recipeName - String for recipe name
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Directions getter
     * @return String - for directions
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Directions setter
     * @param directions - String for directions
     */
    public void setDirections(String directions) {
        this.directions = directions;
    }

    /**
     * Ingredient List getter
     * @return Json for ingredient list
     */
    public ArrayList<String> getIngredientList() {
        return ingredientList;
    }
}
