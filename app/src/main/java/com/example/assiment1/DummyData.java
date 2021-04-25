package com.example.assiment1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyData {

  public static ArrayList<String> arrayList = new ArrayList<>();
  public static ArrayList<String> eventsOn1542021 = new ArrayList<>();

   public DummyData(){
        fillArrayList("2021-4-15" , "2021-4-20");
        eventsOn1542021.add("home work");
        eventsOn1542021.add("online meetings");
   }

   public static void fillArrayList(String d1 , String d2){
       arrayList.add("2021-4-15");
       arrayList.add("2021-4-16");
       arrayList.add("2021-4-17");
       arrayList.add("2021-4-18");

   }

}
