package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;

import java.util.ArrayList;

/**
 * Created by oniononion on 7/3/2016.
 */
public class LocationListViewActivity  extends Activity {

    ArrayList<WeatherInfo> array_list =( ArrayList<WeatherInfo> ) IntentHelper.getObjectForKey("array");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_location_list);

        final ListView listView=(ListView)findViewById(R.id.location_list_view);


    }
}
