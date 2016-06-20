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
    EditText title, details, date, moodText;
    Button submitButton;
    MyDB myDB;

    Boolean mHappy = false;
    Boolean mSad = false;
    Boolean mAngry = false;
    Boolean mFunny = false;

    Boolean dHappy = false;
    Boolean dSad = false;
    Boolean dAngry = false;
    Boolean dFunny = false;

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


        moodText = (EditText) findViewById(R.id.moodText);


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
        String mood = moodText.getText().toString();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        Date date = new Date();

        myDB.insertEntry(Entrytitle, mood, entryDetails, sdf.format(date));
        myDB.close();
        Log.d("Database Updated ", Entrytitle + " added to db " + mood + entryDetails);

    }
}
