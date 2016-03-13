package com.example.oniononion.comp4521project.Currency_converter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.Object.Currency;
import com.example.oniononion.comp4521project.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by oniononion on 11/3/2016.
 */

public class ConverterActivity extends Activity {
    private static final String TAG = ConverterActivity.class.getSimpleName();
    private final String uri = "http://www.x-rates.com/table/?from=JPY&amount=1";
    private ArrayList<Currency> currency_array = new ArrayList<>();
    private ArrayList<String> name_array = new ArrayList<>();
    private ArrayList<String> merger_name_array  = new ArrayList<>();
    private ArrayList<Float> rate_array = new ArrayList<>();
    private HashMap<String, String> hmap = new HashMap<>();
    EditText input;
    TextView from_amount;
    TextView from_type;
    TextView result;
    private int current_position = 15;
    private boolean reversed= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_converter_activity);

        NavigationDrawerInstaller.installOnActivity(this);

        try {
            getDataFromWebsite();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        input = (EditText) findViewById(R.id.currency_edittext);

        Button go = (Button) findViewById(R.id.currency_go_button);
        go.setOnClickListener(buttonClickListener);
        Button reverse = (Button) findViewById(R.id.currency_reverse_button);
        reverse.setOnClickListener(buttonClickListener);

        from_amount = (TextView) findViewById(R.id.currency_from_amout_textview);
        from_type = (TextView) findViewById(R.id.currency_from_type_textview);
        result = (TextView) findViewById(R.id.currency_result_textview);

        calculationResult();
    }

    private void calculationResult() {
        String temp = input.getText().toString();
        float input_float;
        if(isNumeric(temp)&&(!temp.isEmpty())){
            input_float = Float.valueOf(temp);
            if (input_float == Math.round(input_float)) {
                from_amount.setText(temp + ".00");
            } else {
                from_amount.setText(temp);
            }
        }else{
            input_float = 1;
            from_amount.setText("1.00");
        }
        from_type.setText("JPY =");

        float resultAmount = input_float * rate_array.get(current_position);
        result.setText(String.valueOf(resultAmount) + " "+ currency_array.get(current_position).getShort_name());
    }

    private void reverse_calculationResult() {
        String temp = input.getText().toString();
        float input_float;
        if(isNumeric(temp)&&(!temp.isEmpty())){
            input_float = Float.valueOf(temp);
            if (input_float == Math.round(input_float)) {
                from_amount.setText(temp + ".00");
            } else {
                from_amount.setText(temp);
            }
        }else{
            input_float = 1;
            from_amount.setText("1.00");
        }
        from_type.setText( currency_array.get(current_position).getShort_name() +" = ");

        float resultAmount = input_float * (1/rate_array.get(current_position));
        result.setText(String.valueOf(resultAmount) + " JPY");
    }

    public static boolean isNumeric(String str) {
        // check the string is valid input or not
        int dotCount=0;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                if(c=='.'&&dotCount==0){
                    dotCount++;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    private void getDataFromWebsite() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(uri).timeout(5000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements content = doc.select("table.tablesorter.ratesTable");

                Elements tbody = content.first().getElementsByTag("tbody");
                Elements links = tbody.select("tr");
                for (Element link : links) {
                    //Element tempDiv = link.children().first();
                    Element tempTr = link.select("td").first();
                    String currency_name = tempTr.text();
                    Log.d(TAG, "currency name :" + currency_name);
                    Element tempSrc = link.select("td.rtRates").first().children().first();
                    String currency_rate_string = tempSrc.text();
                    float currency_rate = Float.valueOf(currency_rate_string);
                    Log.d(TAG, "currency rate string :" + currency_rate_string);
                    Currency info = new Currency(currency_name, currency_rate);
                    currency_array.add(info);
                }

            }


        });
        thread.start();
        thread.join();
        // get name and rate array from the list get from website
        getNameArrayFromList();
        getRateArrayFromList();

    }


    private void getNameArrayFromList() {
        for (int i = 0; i < currency_array.size(); i++) {
            name_array.add(currency_array.get(i).getFull_name());
        }
        makeHashMap();
    }

    private void getRateArrayFromList() {
        for (int i = 0; i < currency_array.size(); i++) {
            rate_array.add(currency_array.get(i).getExchange_rate());
        }
    }
    private void makeHashMap() {
        //import the string into hashmap
        String short_name[] = {"ARS","AUD","BHD","BWP","BRL","GBP","BND","BGN","CAD","CLP","CNY","COP","HRK","CZK","DKK","AED","EUR","HKD","HUF","ISK","INR","IDR","IRR","ILS","KZT","KWD","EUR","LYD","EUR","MYR","MUR","MXN","NRP","NZD","NOK","OMR","PKR","PHP","PLN","QAR","RON","RUB","SAR","SGD","ZAR","KRW","LKR","SEK","CHF","TWD","THB","TTD","TRY","USD","VEF"};
        String long_name[] = {"Argentine Peso","Australian Dollar","Bahraini Dinar","Botswana Pula","Brazilian Real","British Pound","Bruneian Dollar","Bulgarian Lev","Canadian Dollar","Chilean Peso","Chinese Yuan Renminbi","Colombian Peso","Croatian Kuna","Czech Koruna","Danish Krone","Emirati Dirham","Euro","Hong Kong Dollar","Hungarian Forint","Icelandic Krona","Indian Rupee","Indonesian Rupiah","Iranian Rial","Israeli Shekel","Kazakhstani Tenge","Kuwaiti Dinar","Latvian Lat","Libyan Dinar","Lithuanian Litas","Malaysian Ringgit","Mauritian Rupee","Mexican Peso","Nepalese Rupee","New Zealand Dollar","Norwegian Krone","Omani Rial","Pakistani Rupee","Philippine Peso","Polish Zloty","Qatari Riyal","Romanian New Leu","Russian Ruble","Saudi Arabian Riyal","Singapore Dollar","South African Rand","South Korean Won","Sri Lankan Rupee","Swedish Krona","Swiss Franc","Taiwan New Dollar","Thai Baht","Trinidadian Dollar","Turkish Lira","US Dollar","Venezuelan Bolivar"};
        for(int i=0; i< long_name.length ;i++) {
            hmap.put(long_name[i], short_name[i]);
        }
        updateShortName();
    }

    private void updateShortName() {
        for (int i=0 ; i< currency_array.size();i++) {
            String short_name = hmap.get(currency_array.get(i).getFull_name());
            currency_array.get(i).setShort_name(short_name);
        }
        // only can initialize spinner here because it should wait for thread for getting data from website
        InitializeSpinner();
    }

    private void InitializeSpinner() {
        Spinner fromSpinner = (Spinner) findViewById(R.id.currency_from_spinner);
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Japanese Yen - JPY"});
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);
        fromSpinner.setOnItemSelectedListener(SpinnerListener);

        ArrayList<String> array = merged_name_array(); // get the merger name array by adding the full name and short name
        Spinner toSpinner = (Spinner) findViewById(R.id.currency_to_spinner);
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);
        toSpinner.setSelection(15);  // set the default currency to HKD
        toSpinner.setOnItemSelectedListener(SpinnerListener);
    }

    private ArrayList<String> merged_name_array() {
        for(int i=0;i<currency_array.size();i++) {
            merger_name_array.add(currency_array.get(i).getFull_name() + " - " + currency_array.get(i).getShort_name());
        }
        return merger_name_array;
    }

    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.currency_go_button:
                    reversed=false;
                    calculationResult();
                    break;
                case R.id.currency_reverse_button:
                    if(!reversed){
                        reversed=true;
                        reverse_calculationResult();
                    }else{
                        reversed=false;
                        calculationResult();
                    }
                    break;
                default:
                    break;

            }
        }
    };

    protected Spinner.OnItemSelectedListener SpinnerListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            current_position = position;
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}