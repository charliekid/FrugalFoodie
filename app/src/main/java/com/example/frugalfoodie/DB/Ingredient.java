package com.example.frugalfoodie.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class for creating ingredient object
 */
@Entity(tableName = FFRoom.INGREDIENT_TABLE)
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int ingredientId;
    private String itemName;
    private double price;
    private int quantity;

    /**
     * Constructor
     * @param ingredientId - int got ingredient id
     * @param price - double for price
     * @param quantity - int for quantity
     */
    public Ingredient(int ingredientId, String itemName, double price, int quantity) {
        this.ingredientId = ingredientId;
        this.itemName = itemName;
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
     * Item name getter
     * @return String - for item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Item name setter
     * @param itemName - String for item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
