package com.example.oniononion.comp4521project.Culture_info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

public class CultureFragment extends Fragment {
    private String filePath;
    private WebView cultureWebView;

    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        filePath = getArguments().getString("FILEPATH");

        View v = inflater.inflate(R.layout.culture_content_webview, container, false);

        cultureWebView = (WebView) cultureWebView.findViewById(R.id.culture_webview);
        cultureWebView.loadUrl(filePath);

        title = (TextView)v.findViewById(R.id.title);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static CultureFragment newInstance(CultureType type) {
        CultureFragment myFragment = new CultureFragment();
        Bundle args = new Bundle();
        switch (type) {
            case PEOPLE:
                args.putString("FILEPATH", "file:///android_asset/people.html");
            case RELIGION:
                args.putString("FILEPATH", "file:///android_asset/religion.html");
            case SOCIAL:
                args.putString("FILEPATH", "file:///android_asset/social.html");
        }
        myFragment.setArguments(args);
        return myFragment;
    }

}