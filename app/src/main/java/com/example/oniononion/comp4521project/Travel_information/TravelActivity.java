package com.example.oniononion.comp4521project.Travel_information;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.R;

/**
 * Created by oniononion on 15/3/2016.
 */
public class TravelActivity extends Activity {

    private String result="http://www.hyperdia.com/en/cgi/en/search.html?dep_node=TOKYO&arv_node=NAGOYA&via_node01=&via_node02=&via_node03=&year=2016&month=03&day=15&hour=23&minute=00&search_type=0&search_way=&transtime=undefined&sort=0&max_route=5&faretype=0&ship=off&lmlimit=null&search_target=route&facility=reserved&sum_target=7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_information_activity);

        NavigationDrawerInstaller.installOnActivity(this);

        Button travelButton =(Button)findViewById(R.id.travel_button);



        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelActivity.this, TravelWebViewActivity.class);
                intent.putExtra("url", result);
                startActivity(intent);
            }
        });


    }


}
