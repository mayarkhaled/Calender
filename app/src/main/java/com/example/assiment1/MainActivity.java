package com.example.assiment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button linkButton;
    CompactCalendarView calendarView;
    TextView textView;
    String currentMonth , currentYear;
    ImageButton left , right;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy" , Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declerations of views
         linkButton =   (Button) findViewById(R.id.linkButton);
         calendarView =(CompactCalendarView) findViewById(R.id.simpleCalendarView);
         calendarView.setUseThreeLetterAbbreviation(true);
         textView = findViewById(R.id.monthandyear);
         left = findViewById(R.id.leftImage);
         right = findViewById(R.id.rightImage);
         getCurrentDate();
        STATICS.init();
         calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
             @Override
             public void onDayClick(Date dateClicked) {
                 Intent intent = new Intent(getBaseContext(), DayView.class);
                 SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
                 String formattedDate = df.format(dateClicked);
                 intent.putExtra("date",formattedDate);
                 startActivity(intent);
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    textView.setText(dateFormat.format(firstDayOfNewMonth));
                }
            });
         left.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 clickOnLeft();
             }
         });

         right.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 clickOnRight();
             }
         });
        MarkTheDaysHasEvents();

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : add the link of that button
            }
        });


    }

    private void clickOnRight() {
        calendarView.showNextMonth();
        currentMonth = STATICS.monthes.get(currentMonth).first;
        if(currentMonth.equals("January")){
            int x = Integer.parseInt(currentYear);
            currentYear = String.valueOf(x+1);
        }
    }

    private void clickOnLeft() {
        calendarView.showPreviousMonth();
        currentMonth = STATICS.monthes.get(currentMonth).second;
        if(currentMonth.equals("January")){
            int x = Integer.parseInt(currentYear);
            currentYear = String.valueOf(x-1);
        }
    }

    /**
     *this function marks the days which have events on it
     * it calls ListOfDatesHasEvents which should return list of days which have events .. loop on the list
     * and mark the days
     *
     * to test with the dummy data just un comment the dummy lines and comment the db lines
     * */
    private void MarkTheDaysHasEvents() {
        ArrayList<String> dates = DataBaseWithUI.ListOfDatesHasEvents();
         //DummyData d = new DummyData();
        //ArrayList<String> dates = d.arrayList;
        if (dates != null) {
            for (int i = 0; i < dates.size(); i++) {
                String date = dates.get(i);
                Calendar calendar = Calendar.getInstance();
                String[] str = date.split("-");
                calendar.set(Calendar.YEAR, Integer.parseInt(str[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(str[1])-1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(str[2]));
                long milliTime = calendar.getTimeInMillis();
                Event event = new Event(Color.RED, milliTime, "dd");
                calendarView.addEvent(event);
            }
        }
    }

    private void getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    //    String formattedDate = df.format(c);
        currentMonth = monthFormat.format(c);
        currentYear = YearFormat.format(c);
        textView.setText(currentMonth+"-"+currentYear);
    }
}