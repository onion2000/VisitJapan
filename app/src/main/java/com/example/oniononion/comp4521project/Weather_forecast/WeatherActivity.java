package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.R;


/**
 * Created by oniononion on 6/3/2016.
 */
public class WeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_menu);

        NavigationDrawerInstaller.installOnActivity(this);

        Button translation = (Button) findViewById(R.id.future_weather_forecast);
        translation.setOnClickListener(buttonClickListener);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(buttonClickListener);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(buttonClickListener);


    }

    protected View.OnClickListener buttonClickListener= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.future_weather_forecast:
                    intent = new Intent(WeatherActivity.this, OnedayWeather.class);
                    startActivity(intent);
                    break;
                case R.id.button2:

                    break;
                case R.id.button3:
                    break;
                case R.id.weather_forecast:
                    intent = new Intent(WeatherActivity.this, WeatherActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        }
    };

}
