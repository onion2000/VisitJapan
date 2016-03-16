package com.example.oniononion.comp4521project.Rate_exchange;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oniononion.comp4521project.R;

/**
 * Created by onion on 2016/03/09.
 */
public class RateExchangeActivity extends Activity {

    private final String uri ="http://www.x-rates.com/table/?from=JPY&amount=1" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_exchange_menu);
        Button  enter= (Button)findViewById(R.id.currency_enter);
        enter.setOnClickListener(buttonClickListener);


    }
    protected View.OnClickListener buttonClickListener= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.currency_enter:

                    break;

                default:
                    break;

            }
        }
    };
}
