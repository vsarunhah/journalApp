package com.android.varun.journalentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.varun.journalentry.data.MyDB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class entryActivity extends AppCompatActivity {
    EditText title, details, date;
    ImageButton happy, sad, angry, funny;
    Button submitButton;
    MyDB myDB;
    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initializeViews();
        myDB = new MyDB(this);
    }

    public void initializeViews() {

        title = (EditText) findViewById(R.id.title);
        details = (EditText) findViewById(R.id.detailsText);
        date = (EditText) findViewById(R.id.date);

        submitButton = (Button) findViewById(R.id.submitButton);

        happy = (ImageButton) findViewById(R.id.moodHappy);
        sad = (ImageButton) findViewById(R.id.moodSad);
        angry = (ImageButton) findViewById(R.id.moodAngry);
        funny = (ImageButton) findViewById(R.id.moodFunny);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Happy";
                happy.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });


        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Sad";
                sad.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });


        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Angry";
                angry.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });


        funny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Funny";
                funny.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });

    }



    public void saveToDB() {
        myDB.open();

        String Entrytitle = title.getText().toString();
        String entryDetails = details.getText().toString();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        Date date = new Date();

        myDB.insertEntry(Entrytitle, mood, entryDetails, sdf.format(date));
        myDB.close();
        Log.d("Database Updated ", Entrytitle + " added to db");

    }
}
