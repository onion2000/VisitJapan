package com.example.oniononion.comp4521project.Travel_information;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.oniononion.comp4521project.NavigationDrawerInstaller;
import com.example.oniononion.comp4521project.R;
import com.example.oniononion.comp4521project.ToolbarInstaller;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.Calendar;

/**
 * Created by oniononion on 15/3/2016.
 */
public class TravelActivity extends AppCompatActivity {
    private static final String TAG = TravelActivity.class.getSimpleName();
    private String result="http://www.hyperdia.com/en";
    private Calendar calendar;
    private static int selectedYear, selectedMonth, selectedday, selectedHour, selectedMins;
    EditText fromEditText;
    EditText toEditText;
    MultiStateToggleButton fare_button;
    MultiStateToggleButton search_button;
    CharSequence[] fare_type_list;
    CharSequence[] search_type_list;
    private int fare_index=0;
    private int search_index=0;

    static Button timePicker;
    static Button datePicker;
    private static boolean dateChanged= false;
    private static boolean timeChanged= false;
    final Calendar c = Calendar.getInstance();

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
        setContentView(R.layout.travel_information_activity);

        NavigationDrawerInstaller.installOnActivity(this);
        ToolbarInstaller.installOnActivity(this);

        Button travelButton =(Button)findViewById(R.id.travel_button);

        calendar = Calendar.getInstance();
        timePicker =(Button)findViewById(R.id.time_picker_button);
        datePicker =(Button)findViewById(R.id.date_picker_button);

        fare_button = (MultiStateToggleButton) this.findViewById(R.id.fare_type_toggle_button);
        fare_type_list = new CharSequence[]{"Ticket", "IC"};
        fare_button.setElements(R.array.fare_type_list,0);
        fare_button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                fare_index = position;
            }
        });
        search_button = (MultiStateToggleButton) this.findViewById(R.id.search_type_toggle_button);
        search_type_list = new CharSequence[]{"Departure", "Arrival","Average"};
        search_button.setElements(R.array.search_type_list,0);
        search_button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                search_index = position;
            }
        });

        // TODO: check valid input
        fromEditText= (EditText)findViewById(R.id.from_edittext);
        toEditText =(EditText)findViewById(R.id.to_edittext);

        selectedHour =  c.get(Calendar.HOUR_OF_DAY);
        selectedMins = c.get(Calendar.MINUTE);
        selectedYear = c.get(Calendar.YEAR);
        selectedMonth = c.get(Calendar.MONTH)+1;
        selectedday = c.get(Calendar.DAY_OF_MONTH);

        if(timePicker!=null && datePicker!=null && travelButton!=null) {

            setTime();
            datePicker.setText("Date: " + calendar.get(Calendar.YEAR) + "-"+ (calendar.get(Calendar.MONTH)+1) +  "-"+ calendar.get(Calendar.DAY_OF_MONTH));


            travelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TravelActivity.this, TravelWebViewActivity.class);
                    result = produce_url();
                    intent.putExtra("url", result);
                    startActivity(intent);
                }
            });

        }
    }

    private String produce_url() {

        String Month, Day, Hour, Mins;
        Month = (selectedMonth< 10) ? ( "0"+ String.valueOf(selectedMonth) ) : String.valueOf(selectedMonth);
        Day = (selectedday<10)? ( "0"+ String.valueOf(selectedday) ) : String.valueOf(selectedday);
        Hour =  (selectedHour<10)? ( "0"+ String.valueOf(selectedHour) ) : String.valueOf(selectedHour);
        if(selectedHour==0) Hour ="00";
        Mins =  (selectedMins<10)? ( "0"+ String.valueOf(selectedMins) ) : String.valueOf(selectedMins);
        if(selectedMins==0) Mins ="00";
        String result= "http://www.hyperdia.com/en/cgi/en/search.html?dep_node=" + fromEditText.getText().toString()
                        + "&arv_node=" + toEditText.getText().toString()
                        + "&via_node01=&via_node02=&via_node03=&year=" + String.valueOf(selectedYear)
                        + "&month=" + Month
                        + "&day=" + Day
                        + "&hour=" + Hour
                        + "&minute=" + Mins
                        + "&search_type=" +  search_type_list[search_index].toString()
                        + "&search_way=&transtime=undefined&sort=0&max_route=5&faretype=" + fare_type_list[fare_index].toString()
                        + "&ship=off&lmlimit=null&search_target=route&facility=reserved&sum_target=7";
        return result;
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
            if(!timeChanged) {
                selectedHour = c.get(Calendar.HOUR_OF_DAY);
                selectedMins = c.get(Calendar.MINUTE);
            }
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, selectedHour, selectedMins,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timeChanged = true;
            selectedHour=hourOfDay;
            selectedMins=minute;
            setTime();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
            if(!dateChanged) {
                selectedYear = c.get(Calendar.YEAR);
                selectedMonth = c.get(Calendar.MONTH)+1;
                selectedday = c.get(Calendar.DAY_OF_MONTH);
            }
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, selectedYear, selectedMonth-1,selectedday);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateChanged=true;
            selectedYear=year;
            selectedMonth=monthOfYear+1;
            selectedday=dayOfMonth;
            datePicker.setText("Date: " + selectedYear  + "-"+ selectedMonth+  "-"+ selectedday);

        }
    }

    private static void setTime() {
        if(selectedHour <10 && selectedMins <10){
            timePicker.setText("Time:\n0" + selectedHour + ":0" +selectedMins);
        }else if(selectedHour <10 && selectedMins >10 ){
            timePicker.setText("Time:\n0" + selectedHour + ":" +selectedMins);
        }else if(selectedHour >10 && selectedMins <10){
            timePicker.setText("Time:\n" + selectedHour + ":0" +selectedMins);
        }else {
            timePicker.setText("Time:\n" + selectedHour + ":" + selectedMins);
        }
    }
}
