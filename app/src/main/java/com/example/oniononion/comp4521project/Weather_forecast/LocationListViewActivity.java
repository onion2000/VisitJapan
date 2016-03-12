package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;
import com.telerik.android.primitives.widget.sidedrawer.RadSideDrawer;
import com.telerik.widget.list.ListViewAdapter;
import com.telerik.widget.list.RadListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oniononion on 7/3/2016.
 */
public class LocationListViewActivity  extends Activity {

    ArrayList<WeatherInfo> array_list =( ArrayList<WeatherInfo> ) IntentHelper.getObjectForKey("array");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.weather_forecast_location_list);


        setContentView(R.layout.blank_xml);

        RadSideDrawer drawer = new RadSideDrawer(this);
        drawer.setMainContent(R.layout.weather_forecast_location_list);
        drawer.setDrawerContent(R.layout.main_activity_drawer_side_content);

        ViewGroup rootPanel = (ViewGroup)this.findViewById(R.id.blank);
        rootPanel.addView(drawer);




        RadListView listView = (RadListView)findViewById(R.id.location_listView);
       // List<String> list = Arrays.asList(locationList);

      //  ListViewAdapter listViewAdapter = new ListViewAdapter(getListOfLocations());
        LocationListAdapter locationAdapter = new LocationListAdapter(getListOfLocations());
        listView.setAdapter(locationAdapter);
        listView.addItemClickListener(itemClickListener);
    }


    private List<Location> getListOfLocations() {
        List<Location> Locations = new ArrayList<>();
        // get the location name list from arraylist
        for(int i=0;i<array_list.size();i++){
            String temp= array_list.get(i).getCity_name();
            Locations.add(new Location(temp));
        }
        return Locations;
    }

    RadListView.ItemClickListener itemClickListener = new RadListView.ItemClickListener() {

        @Override
        public void onItemClick(int i, MotionEvent motionEvent) {
            Intent intent = new Intent(getApplicationContext(), OnedayWeather.class);
            intent.putExtra("locationIndex", i);
            // return the same return code 100 that Oneday Weather used to start this activity
            setResult(100, intent);
            // exit from this activity
            finish();
        }

        @Override
        public void onItemLongClick(int i, MotionEvent motionEvent) {

        }

    };


    public class Location {
        private String location;

        public Location(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return String.format("%s", location);
        }
    }
}
