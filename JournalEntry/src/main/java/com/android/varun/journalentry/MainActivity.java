package com.android.varun.journalentry;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent;
import android.widget.SimpleCursorAdapter;
import android.content.DialogInterface;
import com.android.varun.journalentry.data.MyDB;
import com.android.varun.journalentry.data.constants;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static ListView entryList;
    MyDB myDB;
    ImageButton addNewEntry;



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
        addNewEntry = (ImageButton) findViewById(R.id.addEntryButton);
        displayListView();

        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, detailsActivity.class);
                intent.putExtra("clicked_item", position);
                startActivity(intent);
            }
        });

        addNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, entryActivity.class);
                startActivity(i);
            }
        });

        setReminder();

    }

    public void displayListView(){
        Cursor cursor = myDB.getEntries();

        String[] from = new String[]{
                constants.TITLE_NAME,
                constants.DATE_NAME,
//                constants.MOOD,
                constants.DETAIL_NAME
        };


        int[] to = new int[]{
              R.id.rowTitle,
                R.id.rowDate,
//                R.id.mood,
                R.id.rowDetails

        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.row_layout
                , cursor, from, to, 0);
        entryList.setAdapter(adapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id==R.id.delete_All){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Confirm Delete...");
            alertDialog.setMessage("Are you sure you want delete all the entries?");
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    myDB.open();
                    myDB.deleteAll(constants.TABLE_NAME);
                    myDB.close();
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "You clicked on yes", Toast.LENGTH_SHORT).show();
                }

            });


            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    Toast.makeText(getApplicationContext(), "You clicked on no", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            alertDialog.show();
            return true;
        }
        return false;
    }

    public void setReminder(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        Intent notificationMessage = new Intent(this, notificationHelper.class);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, notificationMessage,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }
}
