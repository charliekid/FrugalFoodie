package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.frugalfoodie.DB.FFRoom;
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
    }
}