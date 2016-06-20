package com.android.varun.journalentry;

import android.content.Intent;
import android.database.Cursor;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.SimpleCursorAdapter;

import com.android.varun.journalentry.data.MyDB;
import com.android.varun.journalentry.data.constants;

public class MainActivity extends AppCompatActivity {
    static ListView entryList;
    MyDB myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, entryActivity.class);
//        startActivity(intent);

//        whenever you use this you have to import : import android.content.Intent;
//        and then create an intent like above and then finally startActivity(Intent). Intent intent = new Intent(context, activity you want to start)
//        Alt + Enter (option + return on Mac) to import missing classes like android.content.Intent.
        entryList = (ListView) findViewById(R.id.listEntries);
        myDB = new MyDB(this);
        displayListView();

        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, detailsActivity.class);
                intent.putExtra("clicked_item", position);
                startActivity(intent);
            }
        });

    }

    public void displayListView(){
        Cursor cursor = myDB.getEntries();

        String[] from = new String[]{
                constants.TITLE_NAME,
                constants.DATE_NAME,
                constants.MOOD,
                constants.DETAIL_NAME
        };


        int[] to = new int[]{
              R.id.rowTitle,
                R.id.rowDate,
                R.id.mood,
                R.id.rowDetails

        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.row_layout
                , cursor, from, to, 0);
        entryList.setAdapter(adapter);
        }

}
