package com.example.assiment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DayView extends AppCompatActivity {


    TextView day_textView ;
    ListView listView ;
    ImageButton right;
    ImageButton Left;
    Date d;
    String currentDay , currentMonth , currentYear , date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        day_textView = findViewById(R.id.day);
        listView  = findViewById(R.id.list_of_events);
        right = findViewById(R.id.rightImage);
        Left = findViewById(R.id.leftImage);
        //get intent
        Intent comingIntent = getIntent();
         date = getComingIntentExtras(comingIntent);
        day_textView.setText(date);
        String [] str = date.split("-");
        currentDay = str[0];
        currentMonth = str[1];
        currentYear = str[2];

        FillListView(currentDay , currentMonth , currentYear);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    Date d = sdf.parse(date);
                    c.setTime(Objects.requireNonNull(d));
                    c.add(Calendar.DATE ,1 );
                    date = sdf.format(c.getTime());
                    day_textView.setText(date);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                FillListView(currentDay , currentMonth , currentYear);
            }
        });
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    Date d = sdf.parse(date);
                    c.setTime(Objects.requireNonNull(d));
                    c.add(Calendar.DATE ,-1 );
                    date = sdf.format(c.getTime());
                    day_textView.setText(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FillListView(currentDay , currentMonth , currentYear);
            }
        });



    }

    private String getComingIntentExtras(Intent intent){

       String dayOfMonth =intent.getStringExtra("date");
        return dayOfMonth;
    }



    /**
     * this function fills the day with its event , once the day is clicked
     * */
    private void FillListView(String dayOfMonth , String month , String year){
        ArrayList<Event> arrayOfEvents = DataBaseWithUI.ListOfEvents(dayOfMonth , month , year);
       // DummyData d = new DummyData();
/*
        ArrayList<Event> arrayOfEvents = new ArrayList<>();
        //tests
        Event event = new Event("algo" , Color.GREEN , false);

        arrayOfEvents.add(event);

        Event event2 = new Event("metting ",Color.RED , true);

        arrayOfEvents.add(event2);*/
        if(arrayOfEvents != null) {
            eventsAdabter adapter = new eventsAdabter(DayView.this, arrayOfEvents);
            listView.setAdapter(adapter);
        }
    }
}