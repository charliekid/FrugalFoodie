package com.example.frugalfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.User;
import com.example.frugalfoodie.databinding.ActivityCreateAccountBinding;

public class CreateAccount extends AppCompatActivity {

    private ActivityCreateAccountBinding activityCreateAccountBinding;
    private FFRoom db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FFRoom.getInstance(getApplicationContext());
        activityCreateAccountBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = activityCreateAccountBinding.getRoot();
        setContentView(view);
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

    /**
     * This method is the onClick handler for the signup button. It calls a private function that
     * will validate the user's credentials.
     * @param view
     */
    public void signup(View view) {
        validateCredentials();
    }


    /**
     * This method validates the data the user entered. It calls several helper functions to ensure
     * that the user has entered valid information. If the user's credentials
     * are valid, the user will be added to the database and will be directed back to the main page.
     * If the user's credentials are invalid, error message(s) will display
     * to indicate the error.
     */
    private void validateCredentials() {
        String username = activityCreateAccountBinding.createAccountUsername.getText().toString();
        String password = activityCreateAccountBinding.createAccountPassword.getText().toString();

        Boolean isUsernameValid = isUsernameUnique(username);
        Boolean isPasswordValid = doesPasswordMeetRequirements(password);


        if (isUsernameValid && isPasswordValid && !username.isEmpty() && !password.isEmpty()) {
            db.userDAO().insertUser(new User(username, password));
            User user = db.userDAO().getUserWithUsername(username);
            Log.d("signup", user.getUsername());
            Log.d("signup", user.getPassword());
            Toast t = Toast.makeText(getApplicationContext(), "Signup successful", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            Intent intent = MainActivity.getIntent(getApplicationContext());
            startActivity(intent);
        }
        if (username.isEmpty()) {
            activityCreateAccountBinding.createAccountUsername.setError("Please enter a username");
        }
        if (password.isEmpty()) {
            activityCreateAccountBinding.createAccountPassword.setError("Please enter a password");
        }
        else {
            if (!isUsernameValid) {
                activityCreateAccountBinding.createAccountUsername.setError("Username is already taken.");
            }
            if (!isPasswordValid) {
                activityCreateAccountBinding.createAccountPassword.setError("Password must be 8 characters long. There must be at least 3 letters and 2 numbers.");
            }
        }
    }


    /**
     * This method checks if the username is already in the database.
     * @param username
     * @return true if username is not in the database, false if it is
     */

    private Boolean isUsernameUnique(String username) {
        User existingUser = db.userDAO().getUserWithUsername(username);
        return existingUser == null? true : false;
    }

    /**
     * This method checks if the password is at least 8 characters long and contains 3 letters
     * and 2 numbers.
     * @param password
     * @return true if the password meets requirements, false if it does not
     */
    private Boolean doesPasswordMeetRequirements(String password) {
        int numLetters = 0, numDigits = 0;
        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i<password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                numLetters += 1;
            }
            else if (Character.isDigit(password.charAt(i))) {
                numDigits += 1;
            }
        }
        return (numLetters >= 3 && numDigits >= 2) ? true : false;
    }
}