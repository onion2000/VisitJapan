package com.example.oniononion.comp4521project.Tourist_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

/**
 * Created by ylcheung on 19/5/16.
 */
//restaurant fragment
public class RestaurantFragment extends Fragment {
    private String result="http://www.jnto.go.jp/restaurant-search/eng/list.php";

    TextView title;
    Spinner biglocspin;
    Spinner smalllocspin;
    Spinner distspin;
    Spinner typespin;
    Spinner lowcostspin;
    Spinner highcostspin;
    Button searchbtn;

    String itemname[] = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //linking the layout
        View v = inflater.inflate(R.layout.tourist_restaurant, container, false);
        //linking the objects
        biglocspin = (Spinner) v.findViewById(R.id.big_locations);
        smalllocspin = (Spinner) v.findViewById(R.id.small_locations);
        distspin = (Spinner) v.findViewById(R.id.distance_view);
        typespin = (Spinner) v.findViewById(R.id.restaurant_type);
        lowcostspin = (Spinner) v.findViewById(R.id.lowcost);
        highcostspin = (Spinner) v.findViewById(R.id.highcost);
        searchbtn = (Button) v.findViewById(R.id.search_btn);

        title= (TextView)v.findViewById(R.id.title);

        ArrayAdapter<String> biglocateadapter;
        String[] biglocatearray = getResources().getStringArray(R.array.big_locations);
        biglocateadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, biglocatearray);
        biglocateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        biglocspin.setAdapter(biglocateadapter);
        biglocspin.setOnItemSelectedListener(SpinnerListener); //only this spinner needs action

        ArrayAdapter<String> smalllocateadapter;
        String[] smalllocatearray = getResources().getStringArray(R.array.small_locations);
        smalllocateadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, smalllocatearray);
        smalllocateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smalllocspin.setAdapter(smalllocateadapter);

        ArrayAdapter<String> distadapter;
        String[] disarray = {"100", "250", "500", "1000", "2000", "5000", "10000", "30000", "50000"};
        distadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, disarray);
        distadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distspin.setAdapter(distadapter);

        ArrayAdapter<String> typeadapter;
        String[] typearray = getResources().getStringArray(R.array.RestaurantType);
        typeadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, typearray);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typespin.setAdapter(typeadapter);

        String[] costarray = {"1000", "2000", "3000", "5000", "8000", "10000", "15000", "20000"};

        ArrayAdapter<String> lowcostadapter;
        lowcostadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, costarray);
        lowcostadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lowcostspin.setAdapter(lowcostadapter);

        ArrayAdapter<String> highcostadapter;
        highcostadapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, costarray);
        highcostadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highcostspin.setAdapter(highcostadapter);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TouristActivity.this, TouristWebViewActivity.class);
                result = produce_url();
                intent.putExtra("url", result);
                startActivity(intent);
            }
        });

        return v;
    }

    public void updateSpinner(){
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, itemname);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smalllocspin.setAdapter(toAdapter);
    }

    protected Spinner.OnItemSelectedListener SpinnerListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (biglocspin.getSelectedItemPosition()) {
                case 0:
                    itemname = getResources().getStringArray(R.array.Hokkaido);
                    break;
                case 1:
                    itemname = getResources().getStringArray(R.array.Tohoku);
                    break;
                case 2:
                    itemname = getResources().getStringArray(R.array.Kanto);
                    break;
                case 3:
                    itemname = getResources().getStringArray(R.array.Chubu);
                    break;
                case 4:
                    itemname = getResources().getStringArray(R.array.Kansai);
                    break;
                case 5:
                    itemname = getResources().getStringArray(R.array.Chugoku);
                    break;
                case 6:
                    itemname = getResources().getStringArray(R.array.Shikoku);
                    break;
                case 7:
                    itemname = getResources().getStringArray(R.array.Kyushu);
                    break;
            }
            if (itemname != null) {
                updateSpinner();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int indexOfString(String searchString, String[] domain)
    {
        for(int i = 0; i < domain.length; i++)
            if(searchString.equals(domain[i]))
                return i;

        return -1;
    }


    private String produce_url(){
        Integer big = biglocspin.getSelectedItemPosition() + 1;
        Integer small = indexOfString((String) smalllocspin.getSelectedItem(), getResources().getStringArray(R.array.small_locations));
        String distance = distspin.getSelectedItem().toString();
        String[] typearray = getResources().getStringArray(R.array.RestaurantCode);
        String type = typearray[typespin.getSelectedItemPosition()];
        String high = highcostspin.getSelectedItem().toString();
        String low = lowcostspin.getSelectedItem().toString();

        String url = "http://www.jnto.go.jp/restaurant-search/eng/list.php?area=" + big
                + "&pref_code=" + small
                + "&resort_code=&distance=" + distance
                + "&rst_type%5B%5D=" + type
                + "&rate_min=" + low
                + "&rate_max=" + high
                + "&keyword=&btnSubmitMap.x=70&btnSubmitMap.y=20&view=list";

        return url;
    }
}
