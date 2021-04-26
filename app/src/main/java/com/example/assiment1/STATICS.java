package com.example.assiment1;

import android.util.Pair;

import java.util.HashMap;

public class STATICS {
   public static HashMap<String,Integer > daysOfMonth = new HashMap<>();
   public static HashMap<String, Pair<String , String >> monthes = new HashMap<>();

    public static void init(){
        monthes.put("January" ,new Pair<>("February", "December") );
        monthes.put("February" ,new Pair<>("March", "January") );
        monthes.put("March" ,new Pair<>("April", "February") );
        monthes.put("April" ,new Pair<>("May", "March") );
        monthes.put("May" ,new Pair<>("June", "April") );
        monthes.put("June" ,new Pair<>("July", "May") );
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
}
