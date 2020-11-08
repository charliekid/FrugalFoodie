package com.example.frugalfoodie.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class for creating ingredient object
 */
@Entity(tableName = FFRoom.INGREDIENT_TABLE)
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int ingredientId;
    private double price;
    private int quantity;       // is it 1 item? 3 items? is it 4 lbs?
    private String unit;        // is it lbs, each etc


    /**
     * Constructor
     * @param ingredientId - int got ingredient id
     * @param price - double for price
     * @param quantity - int for quantity
     * @param unit - String representing lbs, each, etc
     */
    public Ingredient(int ingredientId, double price, int quantity, String unit) {
        this.ingredientId = ingredientId;
        this.price = price;
        this.quantity = quantity;
    }



    /**
     * Ingredient id getter
     * @return int - for ingredient id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Ingredient id setter
     * @param ingredientId - in for ingredient id setter
     */
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
     * Price getter
     * @return double for price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price - double for price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Quantity getter
     * @return int - for quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Quantity setter
     * @param quantity - int for quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Unit getter
     * @return String representing the unit of the ingredient
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Unit Setter
     * @param unit - the unit of the of the item (lbs, each)
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
