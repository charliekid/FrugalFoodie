package com.example.frugalfoodie.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Class for creating recipe object
 */
@Entity(tableName = FFRoom.RECIPE_TABLE)
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;
    private String recipeName;
    private String ingredientList;
    private String directions;

    /**
     * Constructor
     * @param recipeName - String for name
     * @param ingredientList - String for ingredient list
     * @param directions - String for directions
     */
    public Recipe(String recipeName, String ingredientList, String directions) {
        this.recipeName = recipeName;
        this.ingredientList = ingredientList;
        this.directions = directions;
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
     * @return String for ingredient list
     */
    public String getIngredientList() {
        return ingredientList;
    }

    /**
     * Ingredient List setter
     * @param ingredientList - String for ingredient list
     */
    public void setIngredientList(String ingredientList){
        this.ingredientList = ingredientList;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", ingredientList='" + ingredientList + '\'' +
                ", directions='" + directions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(recipeName, recipe.recipeName) &&
                Objects.equals(ingredientList, recipe.ingredientList) &&
                Objects.equals(directions, recipe.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName, ingredientList, directions);
    }
}
