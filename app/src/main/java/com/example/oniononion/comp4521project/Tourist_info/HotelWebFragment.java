package com.example.oniononion.comp4521project.Tourist_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.oniononion.comp4521project.R;

/**
 * Created by ylcheung on 23/5/16.
 */
public class HotelWebFragment extends Fragment{
    private WebView cultureWebView;

    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tourist_webview, container, false);

        cultureWebView = (WebView) v.findViewById(R.id.tourist_result_webview);
        cultureWebView.loadUrl("http://m.japan.travel/eng/hotel_search.php");

        cultureWebView.setWebViewClient(new MyBrowser());

        cultureWebView.getSettings().setJavaScriptEnabled(true);    // the website are using javascript
        cultureWebView.getSettings().setLoadWithOverviewMode(true);
        cultureWebView.getSettings().setBuiltInZoomControls(true);  // the webview zoom in/out

        title = (TextView)v.findViewById(R.id.title);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class MyBrowser extends WebViewClient {
        // When clicking any link inside the webpage of webview, should override this function
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
