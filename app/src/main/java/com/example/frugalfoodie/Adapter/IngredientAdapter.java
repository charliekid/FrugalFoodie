package com.example.frugalfoodie.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.Ingredient;
import com.example.frugalfoodie.databinding.ItemIngredientBinding;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// Citation:
// https://www.java67.com/2014/06/how-to-format-float-or-double-number-java-example.html
// https://stackoverflow.com/questions/51515854/check-all-the-check-boxes-in-recycler-view
// https://stackoverflow.com/questions/33434626/get-list-of-checked-checkboxes-from-recyclerview-android
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredients;
    private List<Ingredient> checkedIngredients;
    private ItemIngredientBinding itemIngredientBinding;


    public IngredientAdapter(ArrayList<Ingredient> ingredients, ArrayList<Ingredient> checkedIngredients) {
        this.ingredients = ingredients;
        this.checkedIngredients = checkedIngredients;
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
        final Ingredient currentIngredient = ingredients.get(position);
        itemIngredientBinding.ingredientCheckbox.setText(currentIngredient.getItemName());
        itemIngredientBinding.ingredientCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        checkedIngredients.add(currentIngredient);
                        Log.d("checked ingredient", "adding " + currentIngredient.toString());

                    } else {
                        checkedIngredients.remove(currentIngredient);
                        Log.d("checked ingredient", "removing " + currentIngredient.toString());
                    }

                }
            });
        itemIngredientBinding.ingredientPrice.setText("$" + String.format("%.2f", currentIngredient.getPrice()));

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
