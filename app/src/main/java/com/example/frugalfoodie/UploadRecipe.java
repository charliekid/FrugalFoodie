package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Recipe;
import com.example.frugalfoodie.DB.RecipeDAO;
import com.example.frugalfoodie.databinding.ActivityUploadRecipeBinding;

public class UploadRecipe extends AppCompatActivity {

    private ActivityUploadRecipeBinding activityUploadRecipeBinding;
    private FFRoom db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FFRoom.getInstance(getApplicationContext());
        activityUploadRecipeBinding = ActivityUploadRecipeBinding.inflate(getLayoutInflater());
        View view = activityUploadRecipeBinding.getRoot();
        setContentView(view);



        activityUploadRecipeBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    RecipeDAO dao = FFRoom.getInstance(UploadRecipe.this).recipeDAO();
                    String recipeName = activityUploadRecipeBinding.RecipeName.getText().toString();
                    Recipe recipe1 = dao.getRecipeByName(recipeName);

                    if (recipe1 == null) {
                        Recipe recipe = new Recipe(activityUploadRecipeBinding.RecipeName.getText().toString(),activityUploadRecipeBinding.INgredients.getText().toString(),
                                activityUploadRecipeBinding.editTextTextMultiLine.getText().toString());
                        dao.addRecipe(recipe);
                        Toast.makeText(UploadRecipe.this, recipe.toString() + "Has been added.", Toast.LENGTH_SHORT).show();
                        Intent intent = LandingPage.getIntent(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Toast.makeText(UploadRecipe.this, "Recipe name is already being used", Toast.LENGTH_SHORT).show();
                        activityUploadRecipeBinding.RecipeName.setText("");
                        activityUploadRecipeBinding.INgredients.setText("");
                        activityUploadRecipeBinding.editTextTextMultiLine.setText("");
                    }

                } /**A catch if there is invaild input */
                catch (Exception e) {
                    Toast.makeText(UploadRecipe.this,"Error ", Toast.LENGTH_SHORT).show();
                    activityUploadRecipeBinding.RecipeName.setText("");
                    activityUploadRecipeBinding.INgredients.setText("");
                    activityUploadRecipeBinding.editTextTextMultiLine.setText("");
                }
            }
        });
    }

}