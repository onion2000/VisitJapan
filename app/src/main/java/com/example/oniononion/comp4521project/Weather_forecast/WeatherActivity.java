package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oniononion.comp4521project.R;


/**
 * Created by oniononion on 6/3/2016.
 */
public class WeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_menu);

        Button translation = (Button) findViewById(R.id.today_weather_forecast);
        translation.setOnClickListener(buttonClickListener);
        Button weather_forecast = (Button) findViewById(R.id.button2);
        weather_forecast.setOnClickListener(buttonClickListener);
        Button rate_exchange = (Button) findViewById(R.id.button3);
        rate_exchange.setOnClickListener(buttonClickListener);


    }

    protected View.OnClickListener buttonClickListener= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.today_weather_forecast:
                    Intent intent = new Intent(WeatherActivity.this, OnedayWeather.class);
                    startActivity(intent);
                    break;
                case R.id.button2:

                    break;
                case R.id.button3:
                    break;
                default:
                    break;

            }
        }
    };
}
