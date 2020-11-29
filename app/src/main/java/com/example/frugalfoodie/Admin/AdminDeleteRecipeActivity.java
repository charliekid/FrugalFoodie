package com.example.frugalfoodie.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frugalfoodie.Admin.AdminMenuActivity;
import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.RecipeDAO;
import com.example.frugalfoodie.R;

import java.util.ArrayList;
import java.util.List;

public class AdminDeleteRecipeActivity extends AppCompatActivity {

    Spinner spinner1;
    TextView recipeCountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_recipe);

        addItemsOnSpinner();
        addListenerOnSpinnerItemSelection();
    }



    public void addItemsOnSpinner() {
        // Connect to our db
        RecipeDAO dao = FFRoom.getInstance(AdminDeleteRecipeActivity.this).recipeDAO();

        // Find our spinner
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        List<String> list = new ArrayList<String>();
        List<String> recipeList = dao.getAllRecipeTitles();
        list.add(" ");
        for(String title : recipeList) {
            list.add(title);
        }
//        list.add("list item 1");
//        list.add("list item 2");
//        list.add("list item 3");
//        list.add("list item 4");
//        list.add("list item 5");

        // Set recipe count so we can see the changes
        recipeCountTextView = (TextView) findViewById(R.id.recipeCountTextView);
        recipeCountTextView.setText("Recipe Count: " + recipeList.size());


        // Set the adapter to update with the list.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AdminDeleteRecipeActivity.class);
        return intent;
    }

    /******************************************************************
     *     Inner class
     ******************************************************************/
    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            final String itemSelected = adapterView.getItemAtPosition(i).toString();
            if(!itemSelected.equals(" ")) {
                //    Toast.makeText(adapterView.getContext(),
                //            "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
                //            Toast.LENGTH_SHORT).show();

                // Below was gotten from https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
                // Creates the alert
                AlertDialog.Builder builder1 = new AlertDialog.Builder(adapterView.getContext());
                builder1.setMessage("Delete " + adapterView.getItemAtPosition(i).toString() + "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RecipeDAO rDao = FFRoom.getInstance(AdminDeleteRecipeActivity.this).recipeDAO();
                                rDao.deleteRecipeByName(itemSelected);
                                startActivity(AdminMenuActivity.getIntent(AdminDeleteRecipeActivity.this));
                                //AdminMenuActivity.getIntent(AdminDeleteRecipeActivity.this);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}