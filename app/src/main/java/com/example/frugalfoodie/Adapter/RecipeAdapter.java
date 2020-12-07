package com.example.frugalfoodie.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frugalfoodie.DB.Recipe;

import com.example.frugalfoodie.LandingPage;
import com.example.frugalfoodie.R;
import com.example.frugalfoodie.ViewRecipe;
import com.example.frugalfoodie.databinding.ActivityViewRecipesBinding;
import com.example.frugalfoodie.databinding.ItemRecipesBinding;

import java.util.ArrayList;
import java.util.List;

// Citation:
// https://www.java67.com/2014/06/how-to-format-float-or-double-number-java-example.html
// https://stackoverflow.com/questions/51515854/check-all-the-check-boxes-in-recycler-view
// https://stackoverflow.com/questions/33434626/get-list-of-checked-checkboxes-from-recyclerview-android
// https://stackoverflow.com/questions/33316837/how-to-prevent-items-from-getting-duplicated-when-scrolling-recycler-view
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private ItemRecipesBinding itemRecipeBinding;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemRecipeBinding = ItemRecipesBinding.inflate(inflater);
        View view = itemRecipeBinding.getRoot();
        return new RecipeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        final Recipe currentRecipe = recipes.get(position);
        itemRecipeBinding.recipeTitle.setText(currentRecipe.getRecipeName());
//        itemRecipeBinding.recipeTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = ViewRecipe.getIntent(view.getContext(), currentRecipe.getRecipeId());
//                view.getContext().startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder {

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}