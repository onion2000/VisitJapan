package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.R;
import com.telerik.widget.list.ListViewAdapter;
import com.telerik.widget.list.RadListView;

import java.util.Arrays;

/**
 * Created by onion on 2016/03/08.
 */
public class DateListViewActivity extends Activity {
    String[] array_list = (String[]) IntentHelper.getObjectForKey("list");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_date_list);
        NavigationDrawerInstaller.installOnActivity(this);

        RadListView listView = (RadListView) findViewById(R.id.date_listView);
        //  this.setListAdapter(new ArrayAdapter<>(this, R.layout.weather_forecast_date_list, R.id.date_list_view, array_list));
      //  ListViewAdapter listViewAdapter = new ListViewAdapter(Arrays.asList(array_list));
        DateListAdapter dateAdapter = new DateListAdapter(Arrays.asList(array_list));
        listView.setAdapter(dateAdapter);
        listView.addItemClickListener(itemClickListener);

    }

    RadListView.ItemClickListener itemClickListener = new RadListView.ItemClickListener() {

        @Override
        public void onItemClick(int i, MotionEvent motionEvent) {
            Intent intent = new Intent(getApplicationContext(), OnedayWeather.class);
            intent.putExtra("dateIndex", i+1);
            // return the same return code 200 that OnedayWeather used to start this activity
            setResult(200, intent);
            // exit from this activity
            finish();
        }

        @Override
        public void onItemLongClick(int i, MotionEvent motionEvent) {

        }

    };
}


