package com.example.oniononion.comp4521project.Weather_forecast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;
import com.example.oniononion.comp4521project.ToolbarInstaller;
import com.mikepenz.materialdrawer.Drawer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by oniononion on 6/3/2016.
 */
public class OnedayWeatherActivity extends AppCompatActivity {
    private static final String TAG = OnedayWeatherActivity.class.getSimpleName();
    private final String weather_image_url_prefix ="http://www.jnto.go.jp/weather" ;
    private final String uri = "http://www.jnto.go.jp/weather/eng/index.php?day=";
    private ArrayList<WeatherInfo> array_info = new ArrayList<>();
    private ArrayList<String> locationArray = new ArrayList<>();
    private final int MaximumDay =9;
    private String[] dateList = new String[MaximumDay];
    private int DateIndex = 1;
    private int LocationIndex =0;
    Drawer drawerResult;
    private boolean spinnerInitial=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Transition mEnterTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.enter_transition);
        Transition mReturnTran =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.return_transition);

        getWindow().setEnterTransition(mEnterTran);
        getWindow().setReturnTransition(mReturnTran);
        setContentView(R.layout.weather_forecast_oneday);


        drawerResult= NavigationDrawerInstaller.installOnActivity(this);
        ToolbarInstaller.installOnActivity(this);

        createDateList();
        // use thread to use the data from website, 1 is the current day
        getDataFromWebsite(DateIndex, LocationIndex);

    }

    private void createDateList() {
        String formattedDate ;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM-d EEE", Locale.ENGLISH);
        for (int i = 0; i < 9; i++) {
            c.add(Calendar.DATE, 1);
            formattedDate = df.format(c.getTime());
            dateList[i]=formattedDate;
        }
    }

    private void getDataFromWebsite(final int DateIndex, final int LocIndex) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                array_info.clear(); // clear the array list when change to another day
                Document doc = null;
                try {
                    doc = Jsoup.connect(uri + DateIndex).timeout(5000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(doc!=null) {
                    Element content = doc.getElementById("map-box");

                    Elements links = content.getElementsByTag("a");
                    for (Element link : links) {
                        Element tempDiv = link.children().first();
                        Element tempP = tempDiv.select("p.city-name").first();
                        String city_name = tempP.text();
                        Log.d(TAG, "cityname :" + city_name);
                        Element tempSrc = tempDiv.select("span.icon").first().children().first();
                        String image_url = tempSrc.attr("src");
                        image_url = image_url.substring(2, image_url.length());
                        Log.d(TAG, "image_url :" + image_url);
                        Element tempSpan = tempDiv.select("span.high_temp.span_c").first();
                        String high_temp = tempSpan.text();
                        Log.d(TAG, "high_temp :" + high_temp);
                        Element tempSpan2 = tempDiv.select("span.low_temp.span_c").first();
                        String low_temp = tempSpan2.text();
                        Log.d(TAG, "low_temp :" + low_temp);
                        Element tempSpan3 = tempDiv.select("span.prob_rain").first();
                        String prob_rain = tempSpan3.text();
                        Log.d(TAG, "prob_rain :" + prob_rain);
                        WeatherInfo info = new WeatherInfo(city_name, image_url, high_temp, low_temp, prob_rain);
                        array_info.add(info);
                    }
                    showDetails(LocIndex);
                    if (!spinnerInitial) {
                        spinnerInitial = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                InitializeSpinner();
                            }
                        });
                    }
                }else{
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "Website Not Found", Toast.LENGTH_SHORT).show();

                }

            }
        });
        thread.start();
    }

    private void showDetails(final int index) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WeatherInfo weatherInfo = array_info.get(index);
                TextView highest_temp = (TextView) findViewById(R.id.highest_temp);
                highest_temp.setText(weatherInfo.getHigh_temp());
                TextView lowest_temp = (TextView) findViewById(R.id.lowest_temp);
                lowest_temp.setText(weatherInfo.getLow_temp());
                TextView prob_rain = (TextView) findViewById(R.id.prob_rain);
                prob_rain.setText(weatherInfo.getProb_rain());
                new DownloadImageTask((ImageView) findViewById(R.id.weather_image)).execute(weather_image_url_prefix + weatherInfo.getImage_url());

            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press close the drawer first and if the drawer is closed close the activity
        if (drawerResult != null && drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


    private void InitializeSpinner() {
        for(int i=0;i<array_info.size();i++){
            locationArray.add(array_info.get(i).getCity_name());
        }
        Spinner otherLocationSpinner = (Spinner) findViewById(R.id.other_location_spinner);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationArray);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert otherLocationSpinner != null;
        otherLocationSpinner.setAdapter(locationAdapter);
        otherLocationSpinner.setOnItemSelectedListener(LocationSpinnerListener);

        Spinner otherDaySpinner = (Spinner) findViewById(R.id.other_day_spinner);
        ArrayAdapter<String> otherDayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateList);
        otherDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otherDaySpinner.setAdapter(otherDayAdapter);
        otherDaySpinner.setOnItemSelectedListener(DaySpinnerListener);
    }

    protected Spinner.OnItemSelectedListener LocationSpinnerListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            LocationIndex = position;
            showDetails(LocationIndex);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    protected Spinner.OnItemSelectedListener DaySpinnerListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            DateIndex = position+1;
            getDataFromWebsite(DateIndex,LocationIndex);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };


}
