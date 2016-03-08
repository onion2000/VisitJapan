package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;

import java.util.ArrayList;

/**
 * Created by oniononion on 7/3/2016.
 */
public class LocationListViewActivity  extends ListActivity {

    ArrayList<WeatherInfo> array_list =( ArrayList<WeatherInfo> ) IntentHelper.getObjectForKey("array");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the location name list from arraylist
        String[] locationList = new String[array_list.size()];
        for(int i=0;i<array_list.size();i++){
            locationList[i]= array_list.get(i).getCity_name();
        }
        this.setListAdapter((ListAdapter) new ArrayAdapter<String>(this, R.layout.weather_forecast_location_list, R.id.location_list_view, locationList));


        ListView lv = getListView();
        lv.setOnItemClickListener(
             new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), OnedayWeather.class);
                    intent.putExtra("locationIndex", position);
                    // return the same return code 100 that   OnedayWeather used to start this activity
                    setResult(100, intent);
                    // exit from this activity
                    finish();
                }
        });


    }
}
