package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.oniononion.comp4521project.Culture_info.CultureMenuActivity;
import com.example.oniononion.comp4521project.Currency_converter.ConverterActivity;
import com.example.oniononion.comp4521project.Japanese_vocab.VocabActivity;
import com.example.oniononion.comp4521project.Tourist_info.TouristActivity;
import com.example.oniononion.comp4521project.Translation.TranslationActivity;
import com.example.oniononion.comp4521project.Travel_information.TravelActivity;
import com.example.oniononion.comp4521project.Weather_forecast.OnedayWeatherActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {
    private static AlertDialog.Builder InternetAlertDialog;
    private Activity mActivity = this;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        ImageButton culture = (ImageButton) findViewById(R.id.local_culture);
        culture.setOnClickListener(buttonClickListener);
        ImageButton tourist = (ImageButton) findViewById(R.id.tourist_information);
        tourist.setOnClickListener(buttonClickListener);


        ImageButton exit = (ImageButton) findViewById(R.id.exit);
        exit.setOnClickListener(buttonClickListener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
            if (CheckConnectivityNotification.isOnline(getApplicationContext()) || (v.getId() == R.id.exit)) {

                switch (v.getId()) {
                    case R.id.translation:
                        intent = new Intent(MainActivity.this, TranslationActivity.class);
                        startActivity(intent, options.toBundle());
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
                        intent = new Intent(MainActivity.this, CultureMenuActivity.class);
                        startActivity(intent, options.toBundle());
                        break;
                    case R.id.tourist_information:
                        intent = new Intent(MainActivity.this, TouristActivity.class);
                        startActivity(intent, options.toBundle());
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
            } else {
                CheckConnectivityNotification.checkOnline(mActivity);

            }

        }

    };


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.oniononion.comp4521project/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.oniononion.comp4521project/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
