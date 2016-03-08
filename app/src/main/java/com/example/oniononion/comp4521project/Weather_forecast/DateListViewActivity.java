package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.R;

/**
 * Created by onion on 2016/03/08.
 */
public class DateListViewActivity extends ListActivity{
    String[] array_list =( String[]) IntentHelper.getObjectForKey("list");
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.setListAdapter(new ArrayAdapter<>(this, R.layout.weather_forecast_date_list, R.id.date_list_view, array_list));


            ListView lv = getListView();
            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), OnedayWeather.class);
                            intent.putExtra("dateIndex", position+1);
                            // return the same return code 200 that OnedayWeather used to start this activity
                            setResult(200, intent);
                            // exit from this activity
                            finish();
                        }
                    });
        }
    }


