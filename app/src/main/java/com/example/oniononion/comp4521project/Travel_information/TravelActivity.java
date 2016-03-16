package com.example.oniononion.comp4521project.Travel_information;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.R;

import java.util.Calendar;

/**
 * Created by oniononion on 15/3/2016.
 */
public class TravelActivity extends FragmentActivity {
    private static final String TAG = TravelActivity.class.getSimpleName();
    private String result="http://www.hyperdia.com/en/cgi/en/search.html?dep_node=TOKYO&arv_node=NAGOYA&via_node01=&via_node02=&via_node03=&year=2016&month=03&day=15&hour=23&minute=00&search_type=0&search_way=&transtime=undefined&sort=0&max_route=5&faretype=0&ship=off&lmlimit=null&search_target=route&facility=reserved&sum_target=7";
    private TimePicker timePicker;
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

    public void showTimePickerDialog(View v) {
        DialogFragment TimeFragment = new TimePickerFragment();
        TimeFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v){
        DialogFragment DateFragment = new DatePickerFragment();
        DateFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int monthOfYear = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, monthOfYear,dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // Do something with the time chosen by the user
        }
    }

}
