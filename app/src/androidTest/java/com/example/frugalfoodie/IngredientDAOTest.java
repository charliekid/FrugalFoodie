package com.example.frugalfoodie;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class IngredientDAOTest {

    private FFRoom db;

    private static final String EXPECTED_INGREDIENT_NAME = "Hass Avocados";
    private static final double EXPECTED_PRICE = 5.00;
    private static final int EXPECTED_QUANTITY = 4;
    private static final String EXPECTED_UNIT = "na";


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FFRoom.class).build();
    }
    @After
    public void closeDb() throws IOException {
        db.close();
    }


    @Test
    public void testInsertIngredient() {
        Ingredient anIngredient = new Ingredient(EXPECTED_INGREDIENT_NAME,
                                                 EXPECTED_PRICE,
                                                 EXPECTED_QUANTITY,
                                                 EXPECTED_UNIT );
        db.ingredientDAO().insertIngredient(anIngredient);

        Ingredient actualIngredient = db.ingredientDAO().getIngredient(EXPECTED_INGREDIENT_NAME);
        assertEquals("Expected ingredient name matches actual ingredient name",
                EXPECTED_INGREDIENT_NAME, actualIngredient.getItemName());
        assertEquals("Expected ingredient price matches actual ingredient price",
                        EXPECTED_PRICE, actualIngredient.getPrice(), 0.0);
        assertEquals("Expected ingredient quantity matches actual ingredient quantity",
                EXPECTED_QUANTITY, actualIngredient.getQuantity(), 0.0);
        assertEquals("Expected ingredient unit matches actual ingredient unit",
                EXPECTED_UNIT, actualIngredient.getUnit());
    }

    @Test
    public void testGetAllIngredients() {
        Ingredient ingredient1 = new Ingredient(EXPECTED_INGREDIENT_NAME, EXPECTED_PRICE, EXPECTED_QUANTITY, EXPECTED_UNIT);
        Ingredient ingredient2 = new Ingredient("Bartlett Pears", 0.88, 1, "lb");
        Ingredient ingredient3 = new Ingredient("Red or Green Dandelion", 5.00, 4, "na");

        db.ingredientDAO().insertIngredient(ingredient1);
        db.ingredientDAO().insertIngredient(ingredient2);
        db.ingredientDAO().insertIngredient(ingredient3);

        ArrayList<Ingredient> allIngredients = (ArrayList<Ingredient>) db.ingredientDAO().getAllIngredients();
        assertEquals("There are 3 ingredients in the database", allIngredients.size(), 3);

    }

    @Test
    public void testGetIngredientById() {
        Ingredient ingredient = new Ingredient(EXPECTED_INGREDIENT_NAME, EXPECTED_PRICE, EXPECTED_QUANTITY, EXPECTED_UNIT);
        db.ingredientDAO().insertIngredient(ingredient);

        Ingredient retrievedIngredientByName = db.ingredientDAO().getIngredient(EXPECTED_INGREDIENT_NAME);
        Ingredient retrievedIngredientById = db.ingredientDAO().getIngredientById(retrievedIngredientByName.getIngredientId());

        assertEquals("Name of the ingredient retrieved by name is the same as the name of the ingredient retrieved by id",
                retrievedIngredientByName.getItemName(),
                retrievedIngredientById.getItemName());

        assertEquals("Price of the ingredient retrieved by name is the same as the price of the ingredient retrieved by id",
                retrievedIngredientByName.getPrice(),
                retrievedIngredientById.getPrice(),
                0.0);
        assertEquals("Quantity of the ingredient retrieved by name is the same as the quantity of the ingredient retrieved by id",
                retrievedIngredientByName.getQuantity(),
                retrievedIngredientById.getQuantity());
        assertEquals("Unit of the ingredient retrieved by name is the same as the unit of the ingredient retrieved by id",
                retrievedIngredientByName.getUnit(),
                retrievedIngredientById.getUnit());

    }

    @Test
    public void testDeleteIngredients() {
        Ingredient ingredient = new Ingredient(EXPECTED_INGREDIENT_NAME, EXPECTED_PRICE, EXPECTED_QUANTITY, EXPECTED_UNIT);

        db.ingredientDAO().insertIngredient(ingredient);

        Ingredient ingredientInDatabase = db.ingredientDAO().getIngredient(EXPECTED_INGREDIENT_NAME);
        assertEquals("Haas Avocados successfully retrieved", ingredient.getItemName(), ingredientInDatabase.getItemName());

        db.ingredientDAO().deleteIngredient(ingredientInDatabase);
        assertTrue("Haas Avacodos has been successfully deleted",
                db.ingredientDAO().getIngredient(EXPECTED_INGREDIENT_NAME) == null);

    }

    @Test
    public void testDeleteAllIngredients() {
        Ingredient ingredient1 = new Ingredient(EXPECTED_INGREDIENT_NAME, EXPECTED_PRICE, EXPECTED_QUANTITY, EXPECTED_UNIT);
        Ingredient ingredient2 = new Ingredient("Bartlett Pears", 0.88, 1, "lb");
        Ingredient ingredient3 = new Ingredient("Red or Green Dandelion", 5.00, 4, "na");

        db.ingredientDAO().insertIngredient(ingredient1);
        db.ingredientDAO().insertIngredient(ingredient2);
        db.ingredientDAO().insertIngredient(ingredient3);

        db.ingredientDAO().deleteAllIngredients();
        assertEquals("There are 0 ingredients in the database", db.ingredientDAO().getAllIngredients().size(), 0);

    }


}
