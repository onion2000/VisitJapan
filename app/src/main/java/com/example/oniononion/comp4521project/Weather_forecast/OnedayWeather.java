package com.example.oniononion.comp4521project.Weather_forecast;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oniononion.comp4521project.Object.IntentHelper;
import com.example.oniononion.comp4521project.Object.WeatherInfo;
import com.example.oniononion.comp4521project.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by oniononion on 6/3/2016.
 */
public class OnedayWeather extends Activity {
    private static final String TAG = OnedayWeather.class.getSimpleName();
    private String weather_image_url_prefix ="http://www.jnto.go.jp/weather" ;
    private String uri = "http://www.jnto.go.jp/weather/eng/index.php?day=";
    private ArrayList<WeatherInfo> array_info = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_oneday);

        Button list_view_button = (Button)findViewById(R.id.list_view_button);
        list_view_button.setOnClickListener(buttonClickListener);

        // use thread to use the data from website, 1 is the current day
        getDatafromWebsite(1);
    }

    private void getDatafromWebsite(final int day_index) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
  //              array_info.clear(); // clear the array list when change to another day
                Document doc = null;
                try {
                    doc = Jsoup.connect(uri+day_index).timeout(10000).get();
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
                showDetails(0);
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

    protected View.OnClickListener buttonClickListener= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.list_view_button:
                    Intent intent = new Intent(OnedayWeather.this, LocationListViewActivity.class);
                    IntentHelper.addObjectWithKey(array_info, "array");
                    startActivity(intent);
                    break;

                default:
                    break;

            }
        }
    };
}
