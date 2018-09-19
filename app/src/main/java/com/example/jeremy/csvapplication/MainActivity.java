package com.example.jeremy.csvapplication;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * The Main class that models the basic functionality of the map screen.
 * It has set of Buttons and NestedScrollView to display different information
 * based on what button was pressed.
 */
public class MainActivity extends AppCompatActivity{
    /*An  instance of a BottomSheetBehavior so we can change the
        state in which the BottomSheet is in*/
    private BottomSheetBehavior bottomSheetBehavior;
    /*An instance of a TextView so that we can change the text in the BottomSheet*/
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View bottomSheet = findViewById(R.id.design_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        /*Needed a way to extract and update the TextView in the NestedScrollView*/
        View mainScreen = LayoutInflater.from(this).inflate(R.layout.activity_main, (ViewGroup) bottomSheet, false);
        textView = mainScreen.findViewById(R.id.nested_text_view);

        Button professorButton = findViewById(R.id.professor_button);
        Button locationButton = findViewById(R.id.location_button);
        Button introButton = findViewById(R.id.intro_button);

        professorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                ProfessorInfo pi = readProfessorData();
                textView.setText(pi.toString());
                Log.d("TextView", "professor.toString() " + textView.getText());
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestingLocation il = readInterestingLocation();
                textView.setText(il.toString());
                Log.d("TextView", "location.toString() " + textView.getText());
            }
        });
    }

    /**
     * This method reads from a specific file (there is only one at the moment) that models
     * a professor's information
     * @return an instance of a ProfessorInfo object which holds the text from the file that was read
     */
    private ProfessorInfo readProfessorData(){
        ProfessorInfo professorInfo;
        /*An array of tokens from the CSV files*/
        String[] professorInfoTokens = readCSVFile(getResources().openRawResource(
                R.raw.kreahling_beacon_info));
        /*If the array has tokens in it, populate the professor object*/
        if(professorInfoTokens != null){
            professorInfo = new ProfessorInfo();
            professorInfo.setName(professorInfoTokens[0]);
            professorInfo.setPhone(professorInfoTokens[1]);
            professorInfo.setEmail(professorInfoTokens[2]);
            professorInfo.setOffice(professorInfoTokens[3]);

            return professorInfo;
        }
        return null;
    }

    /**
     * This method reads from a specific file (there is only one at the moment) that models
     * an interesting location in the CS department
     * @return an instance of a InterestingLocation object which holds
     * the text from the file that was read
     */
    private InterestingLocation readInterestingLocation(){
        InterestingLocation il;
        String[] interestingLocationTokens = readCSVFile(
                getResources().openRawResource(R.raw.cs_lab));

        if(interestingLocationTokens != null){
            il = new InterestingLocation();
            il.setTitle(interestingLocationTokens[0]);
            il.setLocation(interestingLocationTokens[1]);
            il.setDescription(interestingLocationTokens[2]);

            return il;
        }
        return null;
    }

    /**
     * A helper function thats takes an InputStream (A specific CSV file) and returns the tokens
     * it gets from the second line of the file
     * @param is The InputStream that we are going to splitting up
     * @return An array of tokens taken from the file
     */
    private String[] readCSVFile(InputStream is){
        /*An instance of a BufferedReader to read the file line bby line*/
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try{
            line = reader.readLine();//skip headers
            String[] tokens = line.split(",");//split the next line,
            is.close();//shouldn't more than 2 lines
            return tokens;
        }catch(IOException ioe){
            Log.wtf("MyActivity", "Error reading CSV file on line " + line);
            ioe.printStackTrace();
        }
        return null;
    }
}
