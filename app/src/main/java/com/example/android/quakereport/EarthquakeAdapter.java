package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
            holder = new ViewHolder();
            holder.magnitudeTextView = convertView.findViewById(R.id.magnitude_text_view);
            holder.primaryLocationTextView = convertView.findViewById(R.id.primary_location_text_view);
            holder.locationOffsetTextView = convertView.findViewById(R.id.location_offset_text_view);
            holder.dateTextView = convertView.findViewById(R.id.date_text_view);
            holder.timeTextView = convertView.findViewById(R.id.time_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Earthquake earthquake = getItem(position);
        if (earthquake != null) {
            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitudeTextView.getBackground();
            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);
            DecimalFormat formatter = new DecimalFormat("0.0");
            holder.magnitudeTextView.setText(formatter.format(earthquake.getMagnitude()));
            String locationOffset;
            String primaryLocation;
            if (earthquake.getLocationText().contains("of")) {
                String[] location = earthquake.getLocationText().split("of");
                locationOffset = location[0].concat(" of");
                primaryLocation = location[1].trim();
            } else {
                locationOffset = "Near the";
                primaryLocation = earthquake.getLocationText();
            }
            holder.locationOffsetTextView.setText(locationOffset);
            holder.primaryLocationTextView.setText(primaryLocation);
            Date date = new Date(earthquake.getTimestamp());
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            holder.dateTextView.setText(dateFormat.format(date));
            dateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            holder.timeTextView.setText(dateFormat.format(date));
        }
        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        Context context = getContext();
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    static class ViewHolder {
        TextView magnitudeTextView;
        TextView primaryLocationTextView;
        TextView locationOffsetTextView;
        TextView dateTextView;
        TextView timeTextView;
    }
}
