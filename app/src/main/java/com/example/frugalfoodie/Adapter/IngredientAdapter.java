package com.example.frugalfoodie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;
import com.example.frugalfoodie.databinding.ItemIngredientBinding;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredients = new ArrayList<>();
    private ItemIngredientBinding itemIngredientBinding;
    private FFRoom db;

    public IngredientAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemIngredientBinding = ItemIngredientBinding.inflate(inflater);
        View view = itemIngredientBinding.getRoot();
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Ingredient currentIngredient = ingredients.get(position);
        itemIngredientBinding.ingredientCheckbox.setText(currentIngredient.getItemName());
        itemIngredientBinding.ingredientPrice.setText(decimalFormat.format(currentIngredient.getPrice()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder {

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
