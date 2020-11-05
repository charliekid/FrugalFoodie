package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CreateAccount extends AppCompatActivity {

//    private ActivityCreateAccountBinding activityCreateAccountBinding;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    /**
     * This method launches the Signup Activity.
     * @param context
     * @return an intent
     */
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CreateAccount.class);
        return intent;
    }
}