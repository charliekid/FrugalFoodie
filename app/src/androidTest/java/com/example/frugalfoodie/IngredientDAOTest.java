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

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IngredientDAOTest {

    private FFRoom db;

    private static final String EXPECTED_INGREDIENT_NAME = "Hass Avocados";
    private static final double EXPECTED_PRICE = 5.00;
    private static final int EXPECTED_QUANTITY = 4;
    private static final String EXPECTED_UNIT = "na";


//    @Before
//    public void createDb() {
//        Context context = ApplicationProvider.getApplicationContext();
//        db = Room.inMemoryDatabaseBuilder(context, FFRoom.class).build();
//    }
//    @After
//    public void closeDb() throws IOException {
//        db.close();
//    }
//
//
//    @Test
//    public void testInsertIngredient() {
//        Ingredient anIngredient = new Ingredient(EXPECTED_INGREDIENT_NAME,
//                                                 EXPECTED_PRICE,
//                                                 EXPECTED_QUANTITY,
//                                                 EXPECTED_UNIT );
//        db.ingredientDAO().insertIngredient(anIngredient);
//
//        Ingredient actualIngredient = db.ingredientDAO().getIngredient(EXPECTED_INGREDIENT_NAME);
//        assertEquals("Expected insertUser does not match actual",
//                        EXPECTED_PRICE, actualIngredient.getPrice());
//    }
}
