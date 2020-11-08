package com.example.frugalfoodie.DB.TypeConverter;

import androidx.room.TypeConverter;
import com.example.frugalfoodie.DB.Ingredient;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataConverter implements Serializable {

    @TypeConverter
    public String fromList(ArrayList<Ingredient> ingredientList){
        if(ingredientList == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ingredient>>(){
        }.getType();
        String json = gson.toJson(ingredientList, type);
            return json;
    }

    @TypeConverter
    public ArrayList<Ingredient> toList(String ingredientListString){
        if (ingredientListString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>(){
        }.getType();
        ArrayList<Ingredient> productList = gson.fromJson(ingredientListString, type);
        return productList;
    }
}

