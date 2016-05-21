package com.example.oniononion.comp4521project.Tourist_info;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ylcheung on 19/5/16.
 */
//restaurant fragment
public class RestaurantFragment extends Fragment {
//
//    TextView title;
//    Spinner biglocspin;
//    Spinner smalllocspin;
//    Spinner distspin;
//    Spinner typespin;
//    Spinner lowcostspin;
//    Spinner highcostspin;
//    Button searchbtn;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        //linking the layout
//        View v = inflater.inflate(R.layout.tourist_restaurant, container, false);
//        //linking the objects
//        biglocspin = (Spinner) v.findViewById(R.id.big_locations);
//        smalllocspin = (Spinner) v.findViewById(R.id.small_locations);
//        distspin = (Spinner) v.findViewById(R.id.distance_view);
//        typespin = (Spinner) v.findViewById(R.id.restaurant_type);
//        lowcostspin = (Spinner) v.findViewById(R.id.lowcost);
//        highcostspin = (Spinner) v.findViewById(R.id.highcost);
//        searchbtn = (Button) v.findViewById(R.id.search_btn);
//
//        title= (TextView)v.findViewById(R.id.title);
//
//        ArrayAdapter<String> biglocateadapter;
//        String[] biglocatearray = getResources().getStringArray(R.array.big_locations);
//
//        biglocateadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, biglocatearray);
//        biglocateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        biglocspin.setAdapter(biglocateadapter);
//        biglocspin.setOnItemSelectedListener(SpinnerListener);
//
//        ArrayAdapter<String> smalllocate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, R.array.small_locations);
//        smalllocate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        smalllocspin.setAdapter(smalllocate);
//        smalllocspin.setOnItemSelectedListener(SpinnerListener);
//
//
//
//
//
//
//        return v;
//    }
//
//    public void updateSpinner(String big_location){
//        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
//        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        smalllocspin.setAdapter(toAdapter);
//        smalllocspin.setSelection(1);  // set the default
//        smalllocspin.setOnItemSelectedListener(SpinnerListener);
//    }
//
//    protected Spinner.OnItemSelectedListener SpinnerListener = new Spinner.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            current_position = position;
//        }
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//    };
//
//    protected View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.search_btn:
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    };
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
