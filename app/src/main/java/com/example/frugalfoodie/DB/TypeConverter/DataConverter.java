package com.example.frugalfoodie.DB.TypeConverter;

import androidx.room.TypeConverter;
import com.example.frugalfoodie.DB.Recipe;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataConverter implements Serializable {

    @TypeConverter
    public String fromOptionValuesList(List<Recipe> ingredientList){
        if(ingredientList == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>(){
        }.getType();
        String json = gson.toJson(ingredientList, type);
            return json;
    }

    @TypeConverter
    public List<Recipe> toOptionValuesList(String ingredientListString){
        if (ingredientListString == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>(){
        }.getType();
        List<Recipe> productList = gson.fromJson(ingredientListString, type);
        return productList;
    }
}

