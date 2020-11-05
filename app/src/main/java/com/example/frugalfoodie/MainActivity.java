package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

// TODO: Imports for DAO ( when done)
/*import com.example.frugalfoodie.model.;
import com.example.frugalfoodie.model.;
import com.example.frugalfoodie.model.;
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**Variables needed for usernames and passwords */
    public static String username= "";
    public static int userid=0;
    public static final String admin_user = "Admin";
    public static final String admin_password = "Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //TODO: Ask why this is giving an error.
//        setSupportActionBar(toolbar);

        //TODO: connect to DAO for loging
        //(MainActivity.this).loadData(this);

        Button login_button = findViewById(R.id.loginbutton_main);
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
                if(username.equals(MainActivity.admin_user) && password.equals(MainActivity.admin_password))
                {
                    //Intent intent = new Intent(MainActivity.this, AdminMenu.class);
                    //startActivity(intent);
                   // is_true= true;
                }

                //TODO: Connect to DAO for USER + USER PAGE TO SEARCH

               //GradeDao dao = GradeRoom.getGradeRoom(MainActivity.this).dao();
                //User user1 = dao.loginUser(username, password);

                ///A loop to check if user is logging in,if log in is successful a welcome message appears containing the user's name
                if(user != null)
                {
                    //MainActivity.username = user.getFirst_name() + " " + user.getLast_name();
                   // MainActivity.userid = user.getUserid();
                    //Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    //startActivity(intent);
                }
                else if(!is_true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("No user found.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Closes alert Dialog after okay clicked
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

               //TODO: CONNECT TO CREATE ACCOUNT
        Button create_login = findViewById(R.id.create_login_button);
        create_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }
}