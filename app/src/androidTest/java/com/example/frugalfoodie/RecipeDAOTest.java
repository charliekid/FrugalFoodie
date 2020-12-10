package com.example.frugalfoodie;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecipeDAOTest {
    private FFRoom db;

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
    public void testInsertRecipe() throws Exception {
        // insert recipe into database
        Recipe recipe = new Recipe("Debb's Recipe", "Love", "Pour love into your food");
        db.recipeDAO().addRecipe(recipe);

        // retrieve recipe from database
        Recipe rRetrieval = db.recipeDAO().getRecipeByName("Debb's Recipe");

        // ensure the retrieved recipe's attributes are correct
        assertEquals(rRetrieval.getRecipeName(), "Debb's Recipe");
        assertEquals(rRetrieval.getIngredientList(), "Love");
        assertEquals(rRetrieval.getDirections(), "Pour love into your food");
    }

}
