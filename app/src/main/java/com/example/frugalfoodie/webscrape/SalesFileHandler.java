package com.example.frugalfoodie.webscrape;

//Needed for BufferedReader to read in a text file
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.example.frugalfoodie.DB.Ingredient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SalesFileHandler {
    public static final String TAG = "SALES_FILE_HANDLER_TAG";
    private Context mContext;

    public SalesFileHandler(Context context) {
        this.mContext = context;
    }


    public void readSalesFile(String filename, List saleList) {
        AssetManager assetManger = mContext.getAssets();

        //Open FileReader and BufferReader
        FileReader fileReader = null;
        BufferedReader inputStream = null;

        try {
            InputStream is = assetManger.open(filename);
            inputStream = new BufferedReader(new InputStreamReader(is));
            Log.d(TAG, "File read");
        } catch(FileNotFoundException e) {
            Log.d(TAG, e.getMessage());
            Log.d(TAG, "Exiting Program");
            System.exit(0);
        } catch(IOException ioe) {
            Log.d(TAG, ioe.getMessage());
            Log.d(TAG, "Exiting Program");
            System.exit(0);
        }

        // The data we will be storing
        String itemName;
        double itemPrice = 0.0;
        String itemUnit = "";
        int itemQty = 0;

        // This try catch is because we will be using is because we using parsers so we need
        // NumberFormatException. And because of .readLine() also throws IOException.
        try {
            String lineOfData;
            while((lineOfData = inputStream.readLine()) != null) {
                String[] saleInfo = lineOfData.split(";");
                itemName = saleInfo[0];

                // Processing (4 lbs./$5) or (6/$10)
                if(saleInfo[1].contains("/")) { // 4 lbs./$5
                    String[] split1 = saleInfo[1].split("/");
                    if(split1[0].contains(" ")) { // 4 lbs.
                        String[] split2 = split1[0].split(" ");
                        itemQty = Integer.parseInt(split2[0]);
                        itemUnit = split2[1];
                        itemPrice = Double.parseDouble(split1[1].substring(1));

                    } else { // 6/$10
                        itemQty = Integer.parseInt(split1[0]);
                        itemPrice = Double.parseDouble(split1[1].substring(1));
                        itemUnit = "na";
                    }
                    // Processing weird (30% off) sales or similar. Lets worry about this later
                } else if (saleInfo[1].contains("OFF")) {
                    Log.d(TAG, "There is a % off and we are skipping this!");
                    itemName = "SKIP";
                    // Processing ($.88 lbs) or ($1 each)
                } else if (saleInfo[1].contains(" ")) {
                    String[] split1 = saleInfo[1].split(" ");
                    itemPrice = Double.parseDouble(split1[0].substring(1));
                    itemUnit = split1[1];
                    itemQty = 1;
                    // Processing any more weird data we get so we don't mess up our data.
                } else
                {
                    itemName = "SKIP";
                }
                // error handling to skip the data we don't want
                if(!itemName.equals("SKIP")) {
                    saleList.add(new Ingredient(itemName, itemPrice, itemQty, itemUnit));
                }
            }
            inputStream.close();
        } catch (IOException ioe) {
            Log.d(TAG, "ERROR in IOException file handler " + ioe.getMessage());
        } catch (NumberFormatException nfe) {
            Log.d(TAG, "ERROR in NumberFormatException file hanlder " + nfe.getMessage());
        }
    }
}
