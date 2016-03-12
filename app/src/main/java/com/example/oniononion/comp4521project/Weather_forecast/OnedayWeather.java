package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;
import com.mikepenz.materialdrawer.Drawer;
import com.telerik.android.primitives.widget.sidedrawer.RadSideDrawer;

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
public class OnedayWeather extends Activity {
    private static final String TAG = OnedayWeather.class.getSimpleName();
    private final String weather_image_url_prefix ="http://www.jnto.go.jp/weather" ;
    private final String uri = "http://www.jnto.go.jp/weather/eng/index.php?day=";
    private ArrayList<WeatherInfo> array_info = new ArrayList<>();
    private final int MaximumDay =9;
    private String[] dateList = new String[MaximumDay];
    private int DateIndex = 1;
    private int LocationIndex =0;
    Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_oneday);

        Button other_location_button = (Button)findViewById(R.id.other_location_button);
        other_location_button.setOnClickListener(buttonClickListener);

        result= NavigationDrawerInstaller.installOnActivity(this);
        createDateList();

        Button other_day_button = (Button)findViewById(R.id.other_day_button);
        other_day_button.setOnClickListener(buttonClickListener);
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
                    doc = Jsoup.connect(uri+DateIndex).timeout(5000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Element content = doc.getElementById("map-box");

                Elements links = content.getElementsByTag("a");
                for (Element link : links) {
                    Element tempDiv = link.children().first();
                    Element tempP = tempDiv.select("p.city-name").first();
                    String city_name = tempP.text();
                    Log.d(TAG, "cityname :"+city_name);
                    Element tempSrc = tempDiv.select("span.icon").first().children().first();
                    String image_url = tempSrc.attr("src");
                    image_url= image_url.substring(2,image_url.length());
                    Log.d(TAG, "image_url :" + image_url);
                    Element tempSpan= tempDiv.select("span.high_temp.span_c").first();
                    String high_temp = tempSpan.text();
                    Log.d(TAG, "high_temp :"+high_temp);
                    Element tempSpan2= tempDiv.select("span.low_temp.span_c").first();
                    String low_temp = tempSpan2.text();
                    Log.d(TAG, "low_temp :"+low_temp);
                    Element tempSpan3= tempDiv.select("span.prob_rain").first();
                    String prob_rain = tempSpan3.text();
                    Log.d(TAG, "prob_rain :"+prob_rain);
                    WeatherInfo info= new WeatherInfo(city_name,image_url,high_temp,low_temp,prob_rain);
                    array_info.add(info);
                }
                showDetails(LocIndex);
            }
        });
        thread.start();
    }

    private void showDetails(final int index) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WeatherInfo weatherInfo = array_info.get(index);
                TextView temperature = (TextView) findViewById(R.id.temperature);
                temperature.setText(weatherInfo.getHigh_temp() + "/" + weatherInfo.getLow_temp());
                TextView prob_rain = (TextView) findViewById(R.id.prob_rain);
                prob_rain.setText(weatherInfo.getProb_rain());
                TextView location = (TextView) findViewById(R.id.location);
                location.setText(weatherInfo.getCity_name());
                TextView date =(TextView) findViewById(R.id.date);
                date.setText(dateList[DateIndex-1]);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100) {
            LocationIndex = data.getExtras().getInt("locationIndex");
            showDetails(LocationIndex);
        }else if(resultCode ==200){
            DateIndex = data.getExtras().getInt("dateIndex");
            getDataFromWebsite(DateIndex,LocationIndex);

        }
    }
    @Override
    public void onBackPressed() {
        //handle the back press close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected View.OnClickListener buttonClickListener= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.other_location_button:
                    Intent intent = new Intent(OnedayWeather.this, LocationListViewActivity.class);
                    IntentHelper.addObjectWithKey(array_info, "array");
                    startActivityForResult(intent, 100);
                    break;
                case R.id.other_day_button:
                    Intent intent2 = new Intent(OnedayWeather.this, DateListViewActivity.class);
                    IntentHelper.addObjectWithKey(dateList, "list");
                    startActivityForResult(intent2, 200);
                default:
                    break;

            }
        }
    };

}
