package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;



import android.content.DialogInterface;
import android.content.Intent;


import com.example.frugalfoodie.DB.UserDAO;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.User;


import androidx.appcompat.app.AlertDialog;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Used for LogCat
    private String TAG = "MAIN_ACTIVITY_TAG";

    /**Variables needed for admin log in */
    public static final String admin_user = "Admin";
    public static final String admin_password = "Admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Inside of main activity");


        Button login_button = findViewById(R.id.loginbutton_main);

      /** Loading Data from UserDAO with all users so that code can check if Username is in dao */
       FFRoom.getInstance(MainActivity.this).loadData(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText user = findViewById(R.id.username_main);
                EditText pass = findViewById(R.id.password_main);

                String username = user.getText().toString();
                String password = pass.getText().toString();
                boolean is_true = false;

                //TODO: make Admin menu

                 //A Loop to check if the Admin is logging in or not, if log in is successful a welcome message appears containing the Admin's name
               // if(username.equals(MainActivity.admin_user) && password.equals(MainActivity.admin_password))
               // {
                    //Intent intent = new Intent(MainActivity.this, AdminMenu.class);
                    //startActivity(intent);
                   // is_true= true;
               // }


                UserDAO dao = FFRoom.getInstance(MainActivity.this).userDAO();
                User user1 = dao.loginUser(username, password);

                /**A loop to check if user is logging in,if log in is successful user will be taken to the main menu of app */
                if(user1 != null)
                {
                   Intent intent = new Intent(MainActivity.this, LandingPage.class);
                   startActivity(intent);
                }
                else if(!is_true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("No user found.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /** Closes alert Dialog after okay clicked */
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

               
        Button create_login = findViewById(R.id.create_login_button);
        create_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}