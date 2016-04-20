package com.example.oniononion.comp4521project;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.oniononion.comp4521project.Currency_converter.ConverterActivity;
import com.example.oniononion.comp4521project.Japanese_vocab.VocabActivity;
import com.example.oniononion.comp4521project.Travel_information.TravelActivity;
import com.example.oniononion.comp4521project.Weather_forecast.OnedayWeatherActivity;


public class MainActivity extends AppCompatActivity {
   // private static Dialog InternetAlertDialog;
    //TODO: change all the id to the String.xml in the res file
    //TODO: all the file name must be consistency
    //TODO: delete all the unnecessary things
    //TODO: write comment
    // TODO: back button in drawer
    // TODO: change dialog to notification
   // TODO: add exit button in drawer
    //TODO: vocal add 50 vocal table
   private static AlertDialog.Builder InternetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
       // Transition mEnterTran = new Fade();
       // Transition mReturnTran = new Fade();
       Transition mReenterTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.main_activity_reenter_transition);
        Transition mExitTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.main_activity_exit_transition);

        getWindow().setExitTransition(mExitTran);
        getWindow().setReenterTransition(mReenterTran);
        setContentView(R.layout.activity_main);

        NavigationDrawerInstaller.installOnActivity(this);
        InternetAlertDialog = new AlertDialog.Builder(this);

        ToolbarInstaller.installOnActivity(this);

        ImageButton translation = (ImageButton) findViewById(R.id.translation);
        translation.setOnClickListener(buttonClickListener);
        ImageButton weather_forecast = (ImageButton) findViewById(R.id.weather_forecast);
        weather_forecast.setOnClickListener(buttonClickListener);
        ImageButton rate_exchange = (ImageButton) findViewById(R.id.rate_exchange);
        rate_exchange.setOnClickListener(buttonClickListener);
        ImageButton travel_info = (ImageButton) findViewById(R.id.travel_info);
        travel_info.setOnClickListener(buttonClickListener);
        ImageButton vocabulary = (ImageButton) findViewById(R.id.vocabulary);
        vocabulary.setOnClickListener(buttonClickListener);




        ImageButton exit = (ImageButton) findViewById(R.id.exit);
        exit.setOnClickListener(buttonClickListener);

    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
            if(isOnline(getApplicationContext())) {

                switch (v.getId()) {
                    case R.id.translation:

                        break;
                    case R.id.weather_forecast:
                        intent = new Intent(MainActivity.this, OnedayWeatherActivity.class);
                        startActivity(intent, options.toBundle());
                        break;
                    case R.id.rate_exchange:
                        intent = new Intent(MainActivity.this, ConverterActivity.class);
                        startActivity(intent, options.toBundle());
                        break;
                    case R.id.local_culture:

                        break;
                    case R.id.tourist_information:

                        break;
                    case R.id.vocabulary:
                        intent = new Intent(MainActivity.this, VocabActivity.class);
                        startActivity(intent, options.toBundle());
                        break;
                    case R.id.travel_info:
                        intent = new Intent(MainActivity.this, TravelActivity.class);
                        startActivity(intent, options.toBundle());
                        break;
                    case R.id.exit:
                        finish();
                        moveTaskToBack(true);
                        System.exit(0);
                        break;
                    default:
                        break;

                }
            }else{

                showInternetDialog();
            }

    }

    };

    public static void showInternetDialog() {
        InternetAlertDialog.setTitle("No connection");
        DialogInterface.OnClickListener closeButton = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        };
        InternetAlertDialog.setPositiveButton("Close",closeButton );
        InternetAlertDialog.show();
    }


    public static boolean isOnline( Context c) {
        // check the devices network connectivity
        ConnectivityManager cm =
                (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
