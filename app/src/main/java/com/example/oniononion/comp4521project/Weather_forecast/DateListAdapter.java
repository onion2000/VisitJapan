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
public class DateListAdapter extends ListViewAdapter {
    public DateListAdapter(List items) {
        super(items);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_forecast_date_list_item, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        DateViewHolder viewHolder = (DateViewHolder)holder;
        String date= (String) getItems().get(position);
        viewHolder.dateView.setText(date);
    }

    public static class DateViewHolder extends ListViewHolder {

        TextView dateView;
        public DateViewHolder (View itemView) {
            super(itemView);

            dateView = (TextView)itemView.findViewById(R.id.date_view);
        }
    }


}