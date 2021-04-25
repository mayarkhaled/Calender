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
    HashMap<String, Pair<String , String > > monthes = new HashMap<>();
    HashMap<String,Integer > daysOfMonth = new HashMap<>();
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

        FillListView(currentDay , currentMonth , currentYear);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysOfMonth.get(currentMonth) >= Integer.parseInt(currentDay)+1 ){
                    int x = Integer.parseInt(currentDay) + 1;
                    currentDay = String.valueOf(x);
                }
                else{
                    //next month
                    currentDay = "01";
                    currentMonth = monthes.get(currentMonth).first;
                }
                if(currentMonth.equals("January") && currentDay == "01"){
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
                if(Integer.parseInt(currentDay)-1 > 1){
                    int x = Integer.parseInt(currentDay) - 1;
                    currentDay = String.valueOf(x);
                }
                else{
                    //prev month
                    currentMonth = monthes.get(currentMonth).second;
                    currentDay = String.valueOf(daysOfMonth.get(currentMonth));
                }
                if(currentMonth.equals("December") && currentDay.equals("31")){
                    int cy = Integer.parseInt(currentYear) - 1 ;
                    currentYear = String.valueOf(cy);
                }
                day_textView.setText(currentDay+"-"+currentMonth+"-"+currentYear);
                FillListView(currentDay , currentMonth , currentYear);
            }
        });

        monthes.put("January" ,new Pair<>("February", "December") );
        monthes.put("February" ,new Pair<>("March", "January") );
        monthes.put("March" ,new Pair<>("April", "February") );
        monthes.put("April" ,new Pair<>("May", "March") );
        monthes.put("May" ,new Pair<>("June", "May") );
        monthes.put("June" ,new Pair<>("July", "June") );
        monthes.put("July" ,new Pair<>("August", "June") );
        monthes.put("August" ,new Pair<>("September", "July") );
        monthes.put("September" ,new Pair<>("October", "August") );
        monthes.put("October" ,new Pair<>("November", "September") );
        monthes.put("November" ,new Pair<>("December", "October") );
        monthes.put("December" ,new Pair<>("January", "November") );

        daysOfMonth.put("January" , 31);
        daysOfMonth.put("February" , 28);
        daysOfMonth.put("March" , 31);
        daysOfMonth.put("April" , 30);
        daysOfMonth.put("May" , 31);
        daysOfMonth.put("June" , 30);
        daysOfMonth.put("July" , 31);
        daysOfMonth.put("August" , 31);
        daysOfMonth.put("September" , 30);
        daysOfMonth.put("October" , 31);
        daysOfMonth.put("November" , 30);
        daysOfMonth.put("December" , 31);


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