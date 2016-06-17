package com.android.varun.journalentry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    ListView entryList;

    String[] planets = new String[] {"Earth", "Venus", "Mercury", "Mars", "Jupiter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, entryActivity.class);
//        startActivity(intent);

//        whenever you use this you have to import : import android.content.Intent;
//        and then create an intent like above and then finally startActivity(Intent). Intent intent = new Intent(context, activity you want to start)
//        Alt + Enter (option + return on Mac) to import missing classes like android.content.Intent.


    }
}
