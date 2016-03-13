package com.example.oniononion.comp4521project.Weather_forecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;
import com.telerik.widget.list.ListViewAdapter;
import com.telerik.widget.list.ListViewHolder;

import java.util.List;

/**
 * Created by oniononion on 11/3/2016.
 */
public class LocationListAdapter extends ListViewAdapter {
    public LocationListAdapter(List items) {
        super(items);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_forecast_location_list_item, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        LocationViewHolder viewHolder = (LocationViewHolder)holder;
        LocationListViewActivity.Location location= (LocationListViewActivity.Location) getItems().get(position);
        viewHolder.locationNameView.setText(location.getLocation());
    }

    public static class LocationViewHolder extends ListViewHolder {

        TextView locationNameView;
        public LocationViewHolder (View itemView) {
            super(itemView);

            locationNameView = (TextView)itemView.findViewById(R.id.location_name_view);
        }
    }
}