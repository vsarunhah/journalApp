package com.android.varun.journalentry;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.content.DialogInterface;
import android.widget.Toast;

import com.android.varun.journalentry.data.MyDB;
import com.android.varun.journalentry.data.constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class entryActivity extends AppCompatActivity {
    EditText title, details, date;
    ImageButton happy, sad, angry, funny;
    Button submitButton;
    MyDB myDB;

    ImageView imageShow;

    String mood = "";

    Boolean mHappy = false;
    Boolean mSad = false;
    Boolean mAngry = false;
    Boolean mFunny = false;

    int position;
    int id;

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
                mHappy = !mHappy;
                if (mHappy == Boolean.TRUE) {
                    happy.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mood += "Happy ";

                }
                if (mHappy == Boolean.FALSE) {

                    happy.setBackgroundColor(getResources().getColor(R.color.white));

                }
            }
        });


        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSad = !mSad;
                if (mSad == Boolean.TRUE) {
                    sad.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mood += "Sad ";

                }
                if (mSad == Boolean.FALSE) {

                    sad.setBackgroundColor(getResources().getColor(R.color.white));


                }

            }
        });


        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAngry = !mAngry;
                if (mAngry == Boolean.TRUE) {
                    angry.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mood += "Angry ";

                }
                if (mAngry == Boolean.FALSE) {

                    angry.setBackgroundColor(getResources().getColor(R.color.white));


                }
            }
        });


        funny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFunny = !mFunny;
                if (mFunny == Boolean.TRUE) {
                    funny.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mood += "Funny ";

                }
                if (mFunny == Boolean.FALSE) {

                    funny.setBackgroundColor(getResources().getColor(R.color.white));


                }
            }
        });




        String comingFrom = getIntent().getStringExtra("coming_from");
        if (comingFrom == null){
            comingFrom = " ";
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveToDB();
                }
            });}

        if (comingFrom.equals("detailsActivity")){
            position = getIntent().getIntExtra("clicked_item", 0);
            SimpleCursorAdapter adapter = (SimpleCursorAdapter) MainActivity.entryList.getAdapter();
            Cursor myCursor = adapter.getCursor();
            myCursor.moveToPosition(position);
            id = myCursor.getInt(myCursor.getColumnIndexOrThrow(constants.KEY_ID));
            title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(constants.TITLE_NAME)));
            details.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(constants.DETAIL_NAME)));
            submitButton.setText("Update Entry");
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDB();
                }
            });

    } }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.entry_menu, menu);

        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
             int id = item.getItemId();
                if (id == R.id.delete_Button){
                    deleteEntryOfDB();
                    return true;
                }
        return false;
    }



    private void updateDB() {
        myDB.open();

        myDB.updateEntry(title.getText().toString(), mood, details.getText().toString(), date.getText().toString(), id);

        myDB.close();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


    private void saveToDB() {
        myDB.open();

        String Entrytitle = title.getText().toString();
        String entryDetails = details.getText().toString();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        Date date = new Date();


            myDB.insertEntry(Entrytitle, mood, entryDetails, sdf.format(date));
            myDB.close();
            Log.d("Database Updated ", Entrytitle + " added to db " + mood + entryDetails);

            Intent intent = new Intent(entryActivity.this, MainActivity.class);
            startActivity(intent);

    }

    private void deleteEntryOfDB(){
        myDB.open();

        myDB.deleteEntry(id);

        myDB.close();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


}
