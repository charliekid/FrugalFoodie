package com.example.frugalfoodie.DB;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import java.util.List;

@Database(entities = {User.class, Recipe.class, Ingredient.class}, version =1)
public abstract class FFRoom extends RoomDatabase {

    private static FFRoom instance;
    public abstract UserDAO userDAO();
    public static final String DB = "FrugalFoodieDB";

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

        }
    }

    private void loadUsers(Context context){
        UserDAO dao = FFRoom.getInstance(context).userDAO();
        User user1 = new User( "Link", "Link4");
        dao.insertUser(user1);
        Log.d("FFRoom", "4 Users added to database");
    }




}
