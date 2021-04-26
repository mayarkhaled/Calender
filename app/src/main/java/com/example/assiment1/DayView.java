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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DayView extends AppCompatActivity {


    TextView day_textView ;
    ListView listView ;
    ImageButton right;
    ImageButton Left;
    Date d;
    String currentDay , currentMonth , currentYear;
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
        String date = getComingIntentExtras(comingIntent);
        day_textView.setText(date);
        String [] str = date.split("-");
        currentDay = str[0];
        currentMonth = str[1];
        currentYear = str[2];
        STATICS.init();
        FillListView(currentDay , currentMonth , currentYear);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(STATICS.daysOfMonth.get(currentMonth) >= Integer.parseInt(currentDay)+1 ){
                    int x = Integer.parseInt(currentDay) + 1;
                    currentDay = String.valueOf(x);
                }
                else{
                    //next month
                    currentDay = "1";
                    currentMonth = STATICS.monthes.get(currentMonth).first;
                }
                if(currentMonth.equals("January") && currentDay.equals("1")){
                    int cy = Integer.parseInt(currentYear) + 1 ;
                    currentYear = String.valueOf(cy);
                }
                day_textView.setText(currentDay+"-"+currentMonth+"-"+currentYear);
                FillListView(currentDay , currentMonth , currentYear);
            }
        });
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(currentDay)-1 >= 1){
                    int x = Integer.parseInt(currentDay) - 1;
                    currentDay = String.valueOf(x);
                }
                else{
                    //prev month
                    currentMonth = STATICS.monthes.get(currentMonth).second;
                    currentDay = String.valueOf(STATICS.daysOfMonth.get(currentMonth));
                }
                if(currentMonth.equals("December") && currentDay.equals("31")){
                    int cy = Integer.parseInt(currentYear) - 1 ;
                    currentYear = String.valueOf(cy);
                }
                day_textView.setText(currentDay+"-"+currentMonth+"-"+currentYear);
                FillListView(currentDay , currentMonth , currentYear);
            }
        });



    }

    private void setCurrents(String date){
        String [] str = date.split("-");
        currentDay = str[0];
        currentMonth = str[1];
        currentYear = str[2];
    }
    private String getComingIntentExtras(Intent intent){

       String dayOfMonth =intent.getStringExtra("date");
      //   d =  new Date(intent.getLongExtra("date" , -1));

        //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        //String formattedDate = df.format(d);
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