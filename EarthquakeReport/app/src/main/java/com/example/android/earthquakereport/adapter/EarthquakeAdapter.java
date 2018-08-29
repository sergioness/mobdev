package com.example.android.earthquakereport.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.earthquakereport.Earthquake;
import com.example.android.earthquakereport.R;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);

        Earthquake currentItem = getItem(position);

        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(String.valueOf(currentItem.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor((int) Math.floor(currentItem.getMagnitude()));
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String location = currentItem.getLocation();
        String separator = "of";
        TextView directionView = listItemView.findViewById(R.id.direction);
        if(location.contains(separator)) {
            String[] parts = location.split(separator);
            if(parts.length == 2) {
                directionView.setText(parts[0]+separator);
                location = parts[1].trim();
            }
        } else
            directionView.setText("Near the");

        TextView locationView = listItemView.findViewById(R.id.location);
        locationView.setText(location);

        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(currentItem.getDate());

        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(currentItem.getTime());

        return listItemView;
    }

    private int getMagnitudeColor(int magnitude) {
        switch (magnitude){
            case 10:
               return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
            case 9:
                return ContextCompat.getColor(getContext(),R.color.magnitude9);
            case 8:
                return ContextCompat.getColor(getContext(),R.color.magnitude8);
            case 7:
                return ContextCompat.getColor(getContext(),R.color.magnitude7);
            case 6:
                return ContextCompat.getColor(getContext(),R.color.magnitude6);
            case 5:
                return ContextCompat.getColor(getContext(),R.color.magnitude5);
            case 4:
                return ContextCompat.getColor(getContext(),R.color.magnitude4);
            case 3:
                return ContextCompat.getColor(getContext(),R.color.magnitude3);
            case 2:
                return ContextCompat.getColor(getContext(),R.color.magnitude2);
            case 1: default:
                return ContextCompat.getColor(getContext(),R.color.magnitude1);
        }
    }
}
