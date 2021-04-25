package com.example.assiment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class eventsAdabter extends ArrayAdapter<Event> {

    public eventsAdabter(Context context, ArrayList<Event> events) {
        super(context, R.layout.event_element, events );

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_element , parent, false);
        }
        TextView event_title = (TextView) convertView.findViewById(R.id.element_title);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_);

        boolean checked = event.isChecked();
        String titleOfElement = event.getEventName();
        event_title.setText(titleOfElement);
        checkBox.setChecked(checked);

        convertView.setBackgroundColor(event.getEventColor());
        return convertView;
    }
}
