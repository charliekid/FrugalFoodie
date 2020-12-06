package com.example.frugalfoodie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

    public class LandingPage extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_landing_page);

            Button upload = findViewById(R.id.upload);

            Button search = findViewById(R.id.search );

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LandingPage.this, UploadRecipe.class);
                    startActivity(intent);
                }
            });

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LandingPage.this, SearchIngredients.class);
                    startActivity(intent);
                }
            });

        }

        }



