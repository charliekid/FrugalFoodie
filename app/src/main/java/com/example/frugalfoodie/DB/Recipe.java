package com.example.frugalfoodie.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating recipe object
 */
@Entity(tableName = FFRoom.RECIPE_TABLE)
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeID;
    private String recipeName;
    private String directions;
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();

    /**
     * Constructor
     * @param recipeID - int for recipeId
     * @param directions - String for directions
     */
    public Recipe(int recipeID, String recipeName, String directions, ArrayList<Ingredient> ingredientList) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.directions = directions;
        this.ingredientList = ingredientList;
    }

    /**
     * Recipe Id getter
     * @return int - for user id
     */
    public int getRecipeID() {
        return recipeID;
    }

    /**
     * Recipe Id setter
     * @param recipeID - int for recipe id
     */
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
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
     * @return String for ingredient list
     */
    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
