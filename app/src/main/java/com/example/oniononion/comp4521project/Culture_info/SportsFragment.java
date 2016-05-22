package com.example.oniononion.comp4521project.Culture_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

/**
 * Created by ylcheung on 22/5/16.
 */
public class SportsFragment extends Fragment {
    private WebView cultureWebView;

    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.culture_content_webview, container, false);

        cultureWebView = (WebView) v.findViewById(R.id.culture_webview);
        cultureWebView.loadUrl("file:///android_asset/sports.html");

        title = (TextView)v.findViewById(R.id.title);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
