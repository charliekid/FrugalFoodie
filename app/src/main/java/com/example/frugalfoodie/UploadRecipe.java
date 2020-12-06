package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

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

                RecipeDAO dao = FFRoom.getInstance(UploadRecipe.this).recipeDAO();
                Recipe recipe1 = dao.getRecipeByName(recipeName);

                try {
                    if(recipe1 != null)
                    {
                        Toast.makeText(UploadRecipe.this,"Recipe already exists ", Toast.LENGTH_SHORT).show(); // non integer input
                    }
                    else {
                        Recipe recipe = new Recipe(recipeName.getText().toString));
                       RecipeDAO.addRecipe(recipe);

                        Toast.makeText(UploadRecipe.this, recipe.toString(), Toast.LENGTH_SHORT).show();
                    }

                } /**A catch if there is invaild input */
                catch (Exception e) {
                    Toast.makeText(UploadRecipe.this,"Error ", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
}