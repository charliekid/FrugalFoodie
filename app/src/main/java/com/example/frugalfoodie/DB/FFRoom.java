package com.example.frugalfoodie.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.TypeConverters;

import androidx.sqlite.db.SupportSQLiteDatabase;

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


    // Used for LogCat
    public static final String TAG = "FFROOM_TAG";

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
        List<Ingredient> ingredientsList = FFRoom.getInstance(context).ingredientDAO().getAllIngredients();
        Log.d(TAG, "the size of the ingreidentList at beginiing of load data" + ingredientsList.size());
        if(user_list.size() ==0) {
            Log.d("FFRoom", "loading data");
            loadUsers(context);
        }
        if(ingredientsList.size() == 0) {
            loadIngredients(context);
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
        SalesFileHandler salesFileHandler = new SalesFileHandler(context);
        // Reads in the data file we have with the weekly sale information
        salesFileHandler.readSalesFile("weeklysale.txt", ingredientList);
        Iterator<Ingredient> ingredientsIterator = ingredientList.iterator();
        while(ingredientsIterator.hasNext()) {
            iDao.insertIngredient(ingredientsIterator.next());
        }
        Log.d(TAG, "ingredients loaded from load ingredients");
    }

    private void loadRecipes(Context context) {
        RecipeDAO rDao = FFRoom.getInstance(context).recipeDAO();

        ArrayList<String> ingredient = new ArrayList<>();
        ingredient.add("Tomato");
        ingredient.add("Steak");
        ingredient.add("Bell Pepper");
        Recipe recipe1 = new Recipe("Steak Fajitas", "Steps", ingredient);
        rDao.addRecipe(recipe1);
    }
}
