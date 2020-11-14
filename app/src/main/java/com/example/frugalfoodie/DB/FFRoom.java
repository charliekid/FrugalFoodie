package com.example.frugalfoodie.DB;

import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.TypeConverters;
import com.example.frugalfoodie.DB.TypeConverter.DataConverter;
import com.example.frugalfoodie.webscrape.SalesFileHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Database(entities = {User.class, Recipe.class, Ingredient.class}, version =1)
@TypeConverters(DataConverter.class)
public abstract class FFRoom extends RoomDatabase {

    private static FFRoom instance;
    public static final String DB = "FrugalFoodieDB";

    /** DAOs */
    public abstract UserDAO userDAO();
    public abstract RecipeDAO recipeDAO();
    public abstract IngredientDAO ingredientDAO();

    /**
     * Tables
     */
    public static final String USER_TABLE = "userTable";
    public static final String RECIPE_TABLE = "recipeTable";
    public static final String INGREDIENT_TABLE = "ingredientTable";

    /**
     * Get app database
     * @param context to context
     * @return the app instance
     */
    public static FFRoom getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FFRoom.class,
                    DB)

                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){
        List<User> user_list = FFRoom.getInstance(context).userDAO().getAllUsers();
        if(user_list.size() ==0)
        {
            Log.d("FFRoom", "loading data");
            loadUsers(context);
            loadIngredients(context);
            loadRecipes(context);

        }
    }

    private void loadUsers(Context context){
        UserDAO dao = FFRoom.getInstance(context).userDAO();
        User user1 = new User( "Link", "Link4");
        dao.insertUser(user1);
        Log.d("FFRoom", "4 Users added to database");
    }

    /**
     * Loads the ingredients into the database
     * @param context
     */
    private void loadIngredients(Context context) {
        IngredientDAO iDao = FFRoom.getInstance(context).ingredientDAO();
        List ingredientList = new ArrayList<Ingredient>();

        // Reads in the data file we have with the weekly sale information
        SalesFileHandler.readSalesFile("weeklysale.txt", ingredientList);
        Iterator<Ingredient> ingredientsIterator = ingredientList.iterator();
        while(ingredientsIterator.hasNext()) {
            iDao.insertIngredient(ingredientsIterator.next());
        }
    }

    /**
     * Loads recipes into the database
     * @param context 
     */
    private void loadRecipes(Context context) {
        RecipeDAO rDao = FFRoom.getInstance(context).recipeDAO();

        Recipe recipe1 = new Recipe("Guacamole", "avocados; lemon; onion; cilantro", "1. Slice avocados lengthwise around the pit. Twist and pull avocados apart to remove the pit. " +
                "2. Using a spoon, scoop out the avocado from the 4 halves into a bowl. " +
                "3. On a cutting board, finely dice the half of the onion until you get around a 1/4 cup to add to the bowl of avocado. " +
                "4. Squeeze half a lemon into the bowl. Start with a little juice and taste test to make sure the lemon juice isn't over powering. I like sour and tart flavors, so I add around 2 Tablespoons. If you aren't a big citrus fan, start with 1 tablespoon. " +
                "5. Add garlic salt to taste. I use around 1/4 tsp. 6. Use a fork to mash the avocado, onion, lemon juice and salt together. " +
                "7. Taste test and adjust as needed. 8. Stir in 1/4 cup chopped cilantro and enjoy! (https://bit.ly/38B4pUW)");
        rDao.addRecipe(recipe1);

        Recipe recipe2 = new Recipe("Dandelion and Sorrel Pesto", "sorrel; dandelion; red onion; lemon; garlic; sunflower seeds; salt; pepper", "Heat up a medium pot of water to boiling. " +
                "Once it is boiling toss in your dandelion greens and cook for about 2-3 minutes and then strain out. In a blender or food processor add all you ingredients, make sure the dandelion has drained well before adding it. " +
                "Process until the pesto is smooth. Store in an airtight container the the refrigerator. Good for about 2 weeks.  I highly recommend that you make this vegan lasagna with it. (https://bit.ly/2GWdgFv)");
        rDao.addRecipe(recipe2);

        Recipe recipe3 = new Recipe("Pasta", "bell pepper; tomatoes; lean ground round; mushrooms; onion; garlic; pasta; oregano; brown sugar; bay leaves; basil", "1. In a large skillet, cook the lean ground round until there is no pink. " +
                "2. Add in chopped onion and garlic, let that cook for 2 minutes. " +
                "3. Add in quartered tomatoes and turn down heat to low so that the juices of the tomatoes are released  " +
                "4. Add in bell peppers, mushrooms, oregano, bay leaves, basil, and brown sugar. Cook until the veggies are soft");
        rDao.addRecipe(recipe3);

        Recipe recipe4 = new Recipe("Fruit Salad", "Pears; Grapes; Apples; Pineapple", "1. Cut up all the fruits you want and put them in a bowl.");
        rDao.addRecipe(recipe4);

        Recipe recipe5 = new Recipe("Marinated Carne Asada", "Marinated Carne Asada", "1. Grill on grill");
        rDao.addRecipe(recipe5);
    }
}
