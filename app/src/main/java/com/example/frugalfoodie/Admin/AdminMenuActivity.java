package com.example.frugalfoodie.Admin;

import androidx.appcompat.app.AppCompatActivity;


// for the inner class
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.RecipeDAO;
import com.example.frugalfoodie.DummyViewRecipes;
import com.example.frugalfoodie.LandingPage;
import com.example.frugalfoodie.MainActivity;
import com.example.frugalfoodie.R;
import com.example.frugalfoodie.ViewRecipes;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuActivity extends AppCompatActivity {

    Button deleteRecipeButton;
    Button dummyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        deleteRecipeButton = (Button) findViewById(R.id.deleteRecipeButton);
        dummyButton = (Button) findViewById(R.id.dummyButton);

        deleteRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AdminDeleteRecipeActivity.getIntent(AdminMenuActivity.this));
            }
        });
        dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(ViewRecipes.getIntent(AdminMenuActivity.this));
                Intent intent = new Intent(AdminMenuActivity.this, ViewRecipes.class);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AdminMenuActivity.class);
        return intent;
    }
}