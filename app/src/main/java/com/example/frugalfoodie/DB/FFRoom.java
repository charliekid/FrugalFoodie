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
            loadRecipes(context);
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

    /**
     * Loads recipes into the database
     * @param context 
     */
    private void loadRecipes(Context context) {
        RecipeDAO rDao = FFRoom.getInstance(context).recipeDAO();

        Recipe recipe1 = new Recipe("Guacamole", "avocados, lemon, onion, cilantro",
                "1. Slice avocados lengthwise around the pit. Twist and pull avocados apart to remove the pit. " +
                        "2. Using a spoon, scoop out the avocado from the 4 halves into a bowl. " +
                        "3. On a cutting board, finely dice the half of the onion until you get around a 1/4 cup to add to the bowl of avocado. " +
                        "4. Squeeze half a lemon into the bowl. Start with a little juice and taste test to make sure the lemon juice isn't over powering. I like sour and tart flavors, so I add around 2 Tablespoons. If you aren't a big citrus fan, start with 1 tablespoon. " +
                        "5. Add garlic salt to taste. I use around 1/4 tsp. " +
                        "6. Use a fork to mash the avocado, onion, lemon juice and salt together. " +
                        "7. Taste test and adjust as needed. 8. Stir in 1/4 cup chopped cilantro and enjoy! (https://bit.ly/38B4pUW)");
        rDao.addRecipe(recipe1);

        Recipe recipe2 = new Recipe("Dandelion and Sorrel Pesto", "sorrel, dandelion, red onion, lemon, garlic, sunflower seeds, salt, pepper",
                "Heat up a medium pot of water to boiling. Once it is boiling toss in your dandelion greens and cook for about 2-3 minutes and then strain out. In a blender or food processor add all you ingredients, make sure the dandelion has drained well before adding it. " +
                        "Process until the pesto is smooth. Store in an airtight container the the refrigerator. Good for about 2 weeks.  I highly recommend that you make this vegan lasagna with it. (https://bit.ly/2GWdgFv)");
        rDao.addRecipe(recipe2);

        Recipe recipe3 = new Recipe("Pasta", "bell pepper, tomatoes, lean ground round, mushrooms, onion, garlic, pasta, oregano, brown sugar, bay leaves, basil",
                "1. In a large skillet, cook the lean ground round until there is no pink. " +
                        "2. Add in chopped onion and garlic, let that cook for 2 minutes. " +
                        "3. Add in quartered tomatoes and turn down heat to low so that the juices of the tomatoes are released  " +
                        "4. Add in bell peppers, mushrooms, oregano, bay leaves, basil, and brown sugar. Cook until the veggies are soft");
        rDao.addRecipe(recipe3);

        Recipe recipe4 = new Recipe("Fruit Salad", "Pears, Grapes, Apples, Pineapple",
                "1. Cut up all the fruits you want and put them in a bowl.");
        rDao.addRecipe(recipe4);

        Recipe recipe5 = new Recipe("Marinated Carne Asada", "Marinated Carne Asada",
                "1. Grill on grill");
        rDao.addRecipe(recipe5);

        Recipe recipe6 = new Recipe("Mashed Potatoes", "potatoes, butter, milk, salt",
                "1. Cut up potatoes to small chunks. 2. Bring a pot water to boil. Once the water boils add the potatoes into the pot. Boil until the potatoes are soft. " +
                        "2. Drain the potatoes and place the potatoes back in the pot. " +
                        "3. Add in butter, milk, salt and garlic. Then mash the potatoes and items together. 4. Serve.");
        rDao.addRecipe(recipe6);

        Recipe recipe7 = new Recipe("Swiss Chard", "2 large bunches Swiss Chard or Rainbow Chard, 2 tablespoon extra-virgin olive oil, 3 cloves garlic finely chopped, 1 large onion diced, 1/2 teaspoon salt, pinch of dry thyme and nutmeg, pepper, 2 teaspoons balsamic vinegar (optional)",
                "1. Chop and clean Swiss Chard: Stack several pieces of Swiss chard on work surface. Remove stems and set aside. Roughly cut leaves into pieces about 2-inch square. Repeat with the remaining swiss chard. Transfer the chopped leaves to a salad spinner filled with water. Drain, repeat washing if necessary, and spin dry. Rinse and chop the Swiss chard stems (about the same size as the diced onion.). " +
                        "2. Cook The Swiss Chard: Heat oil in a large heavy skillet over medium high heat. Add chopped chard stems, garlic, onion, salt, thyme, nutmeg and pepper and cook, stirring often until the onions are starting to brown, 6 to 8 minutes. Add chopped cleaned Swiss chard leaves, 2 tablespoons water and cover. Let wilt, 2 to 4 minutes. " +
                        "Remove lid and continue cooking, stirring occasionally until the Swiss chard is completely wilted and softened, 1 to 3 minutes. Remove from the heat and drizzle with balsamic vinegar if using. Serve hot.(https://bit.ly/35v4S9u)");
        rDao.addRecipe(recipe7);

        Recipe recipe8 = new Recipe("Vegetable Soup", "8 cups vegetable stock, 1 diced onion, 4 diced celery stalks, 1 diced red pepper, 4 diced carrots, 2 diced zucchini, 4 diced tomatoes , 2 cups diced potatoes, 1 cup diced cauliflower, 2 cups spinach roughly chopped, 1 cup green beans, 1 tsp red chili flakes, 2 tsp dried thyme",
                "1. Toss all the ingredients into a large pot and bring to the boil. Season generously with salt and pepper. " +
                        "2. Lower the temperature and simmer for 45 minutes to an hour, depending on how soft you want the veggies. " +
                        "3. Once the vegetables are cooked, taste test and add more salt and pepper if needed. (https://bit.ly/3kpG9HK)");
        rDao.addRecipe(recipe8);

        Recipe recipe9 = new Recipe("Chicken Soup", "1 clove garlic (cut into 3 pieces), 2 tablespoon extra virgin olive oil, 1/8 teaspoon ground thyme, 6 cups chicken broth, 3 cups sliced medium carrots, 3 cups diced celery, 1 cup of cooked chicken, 2 tablespoon of chopped parsley, 1/4 teaspoon fine salt",
                "1. Place olive oil in a soup pot along with the garlic on the medium-low heat. Heat for 2 minutes and then remove the garlic. (Note: removing the garlic makes the recipe low fodmap. I also make this recipe sometimes without any garlic at all.) " +
                        "2. Add the celery, carrots, and thyme and cook the vegetables for about 5 minutes on medium-low heat until they become softened.  (Another option:  skip the olive oil and cook the celery and carrots in several cups of chicken bone broth for about 20 minutes until tender.) " +
                        "3. Add the chicken broth, salt, and pepper and continue to cook on medium-low for about 20 minutes. " +
                        "4. Add shredded chicken and parsley and cook another 5 minutes until completely heated. " +
                        "5. It is ready to serve. Can be stored for leftovers also. (https://bit.ly/3eWQf1E)");
        rDao.addRecipe(recipe9);

        Recipe recipe10 = new Recipe("Collard Green", "1 large bunch of collard greens, 1 1/2 tablespoon extra virgin olive oil, 1/4 teaspoon sea salt, 2 medium minced garlic cloves, pinch of red pepper flakes, a couple lemon wedges",
                "1. To prepare the collards: Cut out the thick center rib out of each collard green. Stack the rib-less greens and roll them up into a cigar-like shape. Slice over the “cigar” as thinly as possible (⅛″ to ¼″) to make long strands. Shake up the greens and give them a few chops so the strands aren’t so long. " +
                        "2. Heat a large, heavy-bottomed skillet over medium-high heat, then add the olive oil. Once the oil is shimmering, add all of the collard greens and the salt. " +
                        "3. Stir until all of the greens are lightly coated in oil, then let them cook for about 30 seconds before stirring again. Continue stirring in 30-second intervals until the greens are wilted, dark green, and some are starting to turn browns on the edges (this is delicious). This will take between 3 to 6 minutes. " +
                        "4. Once the collards are just about done, add the garlic and red pepper flakes (if using). Stir to break up the garlic and cook until it’s fragrant, about 30 seconds. Remove the pan from the heat. " +
                        "5. Immediately divide the cooked collards onto plates, and serve with a lemon wedge each. (https://bit.ly/3eZGTT1)");
        rDao.addRecipe(recipe10);

        Recipe recipe11 = new Recipe("Pork Chops", "4 pork chops, Italian seasoning, salt and pepper to taste, 1 tablespoon olive oil for searing",
                "1. Pat chops dry with the paper towel and sprinkle them very generously with Italian seasoning, salt and pepper.  " +
                        "2. Heat your skillet (I recommend cast iron one) to medium high and add the olive oil. When it's shimmering hot and not a moment before, add your chops. " +
                        "3. Sear on one side, without moving them at all, until they are nice and golden brown on the first side, about 3-5 minutes. Flip and sear the other side until browned.  " +
                        "4. The easiest way to check if the pork chop is done is to use meat thermometer. When they reach an internal temperature of 135 F degrees they are done.  " +
                        "5. Remove them from the skillet and let them rest on a plate for 10 minutes, during which time they will continue cooking with the residual heat and will eventually reach 145 F degrees. " +
                        "6. Do remember that cooking time can vary based on the thickness of the pork chops.(https://bit.ly/2IEeCW5)");
        rDao.addRecipe(recipe11);

        Recipe recipe12 = new Recipe("Smoked Salmon Fennel Salad", "",
                "It's already made enjoy!");
        rDao.addRecipe(recipe12);

        Recipe recipe13 = new Recipe("Meatball Soup", "1 head of escarole cleaned and chopped, 2 diced celery ribs, 1 cup of chopped carrots, 1/2 cup chopped onions, meatballs, 1 gallon of chicken broth, 1 tablespoon olive oil",
                "1. Heat up a large pot on medium with some olive oil.  Add onions, celery, carrots. Heat until the fragrance it released about 3-4 minutes. " +
                        "2. Add in the 1 gallon of chicken stock and turn the heat up to high. Once it begins to boil, then lower the heat down to medium-low. " +
                        "3. Add in the escarole and the meatballs. Cook until the meatballs are cooked through.");
        rDao.addRecipe(recipe13);

        Recipe recipe14 = new Recipe("Thai Coconut Curry", "1 pound medium or large shrimp peeled and deveined, 2 large carrots peeled and sliced into 1/2 inch chunks, 1 red bell pepper diced, 1 teaspoon minced garlic, 1/4 teaspoon cayenne pepper or to taste, salt to taste, 3 cups baby spinach leaves, fresh basil or cilantro or green onions for garnish, 2 tablespoons vegetable oil, 1/2 yellow onion finely chopped, 2 teaspoons fresh ginger OR 1 teaspoon ground ginger, 1/2 teaspoon ground turmeric, 1 tablespoon red curry paste, 1 tablespoon sugar, " +
                "1 15-ounce can coconut milk or fresh coconut milk, 2 tablespoons fish sauce, grated zest and juice of one lime, cooked noodles or rice for serving optional",
                "1. Make the sauce: Heat 1 tablespoon of the oil in a medium skillet over medium heat. Add the onion and cook 5 minutes, or until softened. " +
                        "2. Add the ginger, turmeric, curry paste and sugar and stir until fragrant, about 15 seconds. Pour in the coconut milk and fish sauce. Bring to a simmer, then lower heat and cook 10 minutes to thicken slightly. Remove from the heat, stir in the lime zest and juice. Cover to keep warm. " +
                        "3. Prepare the shrimp: Heat the remaining tablespoon oil in a large (12-inch) skillet over medium-heat. Add the carrots and stir to coat with the oil. Lower the heat, cover the pan and cook the carrots 5 minutes, until tender. " +
                        "4. Turn up the heat to medium-high and add the red bell pepper, shrimp, garlic, cayenne and salt to taste. Cook until the shrimp are no longer pink, 2-3 minutes per side. " +
                        "5. Turn up the heat to medium-high and add the red bell pepper, shrimp, garlic, cayenne and salt to taste. Cook until the shrimp are no longer pink, 2-3 minutes per side. (https://bit.ly/2UsGper)");
        rDao.addRecipe(recipe14);

        Recipe recipe15 = new Recipe("Smoked Trueky", "Smoked Turkey Thighs",
                "These are already ready to eat! Enjoy!");
        rDao.addRecipe(recipe15);

        Recipe recipe16 = new Recipe("Charlie's Beef Rib Eye Steak", "Rib Eye Steak, 1 stick of thyme, 1/2 a stick of butter, salt, pepper, 2 cloves of garlic",
                "1. Let the steak sit out of the fridge for about 30 minutes or until it is room temperature. You can skip this step if you are running short on time. " +
                        "2. Start heating up your pan (preferably a cast iron) while you prep the steak. " +
                        "3. Take your steak and wrap it in the paper towel to soak up any water. Then rub salt and pepper generously. " +
                        "4. Once your pan is heated up turn it down to medium heat and add a slab of butter. Once the butter is melted. Place your steak on pan with the garlic and thyme. Flip the steak every 3 minutes until you get your desired temperature. Take notice of the thyme and garlic, once they begin to burn, take them out of the pan. " +
                        "5. Once the steak is done, pull it of and place on a a plate covered in foil to allow it to rest. 6. Enjoy!");
        rDao.addRecipe(recipe16);

        Recipe recipe17 = new Recipe("Bacon", "Bacon",
                "1. Place on a sheet and put in oven at 375 degrees. No need to preheat");
        rDao.addRecipe(recipe17);

        Recipe recipe18 = new Recipe("Sausage", "Sausage",
                "1. In a sauce pan, bring an inch of water to a boil. " +
                    "2. Once boil, lower it to simmer and add in sausage for about 3 minutes. " +
                    "3. Remove the water and place the pan back on the stove at medium heat. Add in sausage and pan fry for about 3-5 more minutes. Until you get a char on the skin. " +
                    "4. Enjoy");
        rDao.addRecipe(recipe18);

        Recipe recipe19 = new Recipe("Maui or Korean Flanken Ribs", "Maui or Korean Style Beef Flanken Ribs",
                "1. Toss them on the grill until it comes to the desired temperature");
        rDao.addRecipe(recipe19);

        Recipe recipe20 = new Recipe("Teriyaki Style or Lemon Garlic Chicken Kabobs", "Teriyaki Style or Lemon Garlic Chicken Kabobs",
                "1. Toss them on the grill until it comes to the internal temperature is 165 degrees");
        rDao.addRecipe(recipe20);

        Recipe recipe21 = new Recipe("Mahi Mahi Fillets", "Mahi Mahi Fillets, olive oil, lemon, dill",
                "1. Preheat the oven at 400. " +
                        "2. In an aluminium packet, place in fillets, a slice of lemon, dill and olive oil. " +
                        "3. Place in oven for 25-30 minutes. " +
                        "4. Enjoy");
        rDao.addRecipe(recipe21);

        Recipe recipe22 = new Recipe("Pacific Rockfish Fillets", "Pacific Rockfish Fillets, olive oil, lemon, dill",
                "1. Preheat the oven at 400. " +
                        "2. In an aluminium packet, place in fillets, a slice of lemon, dill and olive oil. " +
                        "3. Place in oven for 25-30 minutes. 4. Enjoy");
        rDao.addRecipe(recipe22);

        Recipe recipe23 =  new Recipe("Faroe Island Salmon", "Faroe Island Salmon",
                "1. Preheat the oven at 400. " +
                        "2. In an aluminium packet, place in Salmon, a slice of lemon, dill and olive oil. " +
                        "3. Place in oven for 25-30 minutes. 4. Enjoy");
        rDao.addRecipe(recipe23);

        Recipe recipe24 = new Recipe("Hawaiian Kanpachi", "Hawaiian Kanpachi",
                "1. Preheat the oven at 400. " +
                        "2. In an aluminium packet, place in Kanpachi, a slice of lemon, dill and olive oil. " +
                        "3. Place in oven for 25-30 minutes. 4. Enjoy");
        rDao.addRecipe(recipe24);

        Recipe recipe25 = new Recipe("Slow Cooker Beef Short Ribs", "4 pounds boneless or bone-in beef short ribs about 8 short ribs, 1 teaspoon salt, 1 teaspoon black pepper, 3 cups beef broth, 1/4 cup worcestershire sauce, 1 teaspoon garlic powder, 1 teaspoon onion powder, 1 sprig fresh rosemary",
                "1. Season the short ribs with salt and pepper. Heat a large skillet over high heat. Sear the short ribs on each of the 4 sides for about 60 seconds per side. " +
                        "2. Pour the beef broth, worcestershire sauce, garlic powder, and onion powder into a slow cooker and stir together. Place the short ribs into the liquid in the slow cooker. " +
                        "3. (https://bit.ly/36IJPQ0)");
        rDao.addRecipe(recipe25);
    }
}
