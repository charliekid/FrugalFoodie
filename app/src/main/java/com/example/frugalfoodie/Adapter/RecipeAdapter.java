package com.example.frugalfoodie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frugalfoodie.DB.Recipe;

import com.example.frugalfoodie.databinding.ActivityViewRecipesBinding;

import java.util.ArrayList;
import java.util.List;

// Citation:
// https://www.java67.com/2014/06/how-to-format-float-or-double-number-java-example.html
// https://stackoverflow.com/questions/51515854/check-all-the-check-boxes-in-recycler-view
// https://stackoverflow.com/questions/33434626/get-list-of-checked-checkboxes-from-recyclerview-android
// https://stackoverflow.com/questions/33316837/how-to-prevent-items-from-getting-duplicated-when-scrolling-recycler-view
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private ActivityViewRecipesBinding viewRecipeBinding;


    public RecipeAdapter(List<String> getAllRecipeTitles, List<Recipe> recipes) {
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        viewRecipeBinding = viewRecipeBinding.inflate(inflater);
        View view = viewRecipeBinding.getRoot();
        return new RecipeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        final Recipe currentRecipe = recipes.get(position);
        // viewRecipeBinding.recipeCheckbox.setText(currentRecipe.getRecipeName());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // getItemId and getItemViewType will prevent recyclerView from duplicating ingredient items
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