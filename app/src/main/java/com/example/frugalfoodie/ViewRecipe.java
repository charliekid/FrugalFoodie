package com.example.frugalfoodie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;

public class ViewRecipe extends AppCompatActivity {

    private FFRoom db;
    TextView title;
    TextView ingredients;
    TextView directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        db = FFRoom.getInstance(getApplicationContext());

        //ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        title = findViewById(R.id.titleText);
        ingredients = findViewById(R.id.ingredientsText);
        directions = findViewById(R.id.directionsText);

        // Passed Data: Get intents from ViewRecipes
        getRecipe();
        Intent intent = getIntent();
        String aTitle = intent.getStringExtra("title");
        String aIngredient = intent.getStringExtra("ingredients");
        String aDirection = intent.getStringExtra("directions");

        title.setText(aTitle);
        ingredients.setText(aIngredient);
        directions.setText(aDirection);

        actionBar.setTitle(aTitle);
    }

    private void getRecipe() {
        db.recipeDAO().getRecipeById(1);
    }


}