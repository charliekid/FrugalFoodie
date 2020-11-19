package com.example.frugalfoodie.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    private String unit;

    /**
     * Constructor
     * @param itemName - the name of the ingredient
     * @param price - the price of the ingredient
     * @param quantity - how many items in the ingredient
     * @param unit - String representing lbs, each, etc.
     */
    public Ingredient(String itemName, double price, int quantity, String unit) {
        this.itemName = itemName;
        this.ingredientId = ingredientId;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
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


    /**
     * Unit getter
     * @return unit - String of the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Unit Setter
     * @param unit - String representing lbs, each, etc.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
