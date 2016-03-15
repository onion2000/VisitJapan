package com.example.oniononion.comp4521project;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.oniononion.comp4521project.Currency_converter.ConverterActivity;
import com.example.oniononion.comp4521project.Travel_information.TravelActivity;
import com.example.oniononion.comp4521project.Weather_forecast.WeatherActivity;
import com.mikepenz.materialdrawer.Drawer;


public class MainActivity extends AppCompatActivity {
    //TODO: change all the id to the String.xml in the res file
    //TODO: all the file name must be consistency
    //TODO: delete all the unnecessary things
    //TODO: write comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationDrawerInstaller.installOnActivity(this);

        Button translation = (Button) findViewById(R.id.translation);
        translation.setOnClickListener(buttonClickListener);
        Button weather_forecast = (Button) findViewById(R.id.weather_forecast);
        weather_forecast.setOnClickListener(buttonClickListener);
        Button rate_exchange = (Button) findViewById(R.id.rate_exchange);
        rate_exchange.setOnClickListener(buttonClickListener);
        Button travel_info = (Button) findViewById(R.id.travel_info);
        travel_info.setOnClickListener(buttonClickListener);


    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.translation:

                    break;
                case R.id.weather_forecast:
                    intent = new Intent(MainActivity.this, WeatherActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rate_exchange:
                    intent = new Intent(MainActivity.this, ConverterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.travel_info:
                    intent = new Intent(MainActivity.this, TravelActivity.class);
                    startActivity(intent);
                    break;

                case R.id.exit:
                finish();
                System.exit(0);
                    break;
                default:
                    break;

            }

    }

    };

}
