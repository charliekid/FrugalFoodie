package com.example.frugalfoodie;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/** A class to check conectivity for now can delete later */
    public class DummyClass extends AppCompatActivity {

        TextView hallo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dummy);

            hallo = (TextView) findViewById(R.id.hello);


        }

    }

