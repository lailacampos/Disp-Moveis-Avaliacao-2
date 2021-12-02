package com.laila.unifacs.avaliacao1.activity;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laila.unifacs.avaliacao1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Location> locationList;

    public MyRecyclerViewAdapter(Context context, List<Location> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    private String formatLocationToString(Location location) {

        StringBuilder stringBuilder = new StringBuilder();
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());

        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        stringBuilder.append("Latitude: ");
        stringBuilder.append(latitude);
        stringBuilder.append(", Longitude: ");
        stringBuilder.append(longitude);
        stringBuilder.append(", Time: ");
        stringBuilder.append(df.format(new Date(location.getTime())));

        String result = stringBuilder.toString();

        return result;
    }

    // Inflates the row layout from xml when needed
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    // Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Location location = locationList.get(i);
        String locationData = formatLocationToString(location);
        myViewHolder.locationTextView.setText(locationData);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView locationTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.location_recyclerview_row_TextView);
        }
    }
}
