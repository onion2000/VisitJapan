package com.example.oniononion.comp4521project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.oniononion.comp4521project.Weather_forecast.WeatherActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button translation = (Button) findViewById(R.id.translation);
        translation.setOnClickListener(buttonClickListener);
        Button weather_forecast = (Button) findViewById(R.id.weather_forecast);
        weather_forecast.setOnClickListener(buttonClickListener);
        Button rate_exchange = (Button) findViewById(R.id.rate_exchange);
        rate_exchange.setOnClickListener(buttonClickListener);


    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.translation:

                    break;
                case R.id.weather_forecast:
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rate_exchange:
                    break;
                default:
                    break;

            }
        }
    };

}
