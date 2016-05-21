package com.example.oniononion.comp4521project.Tourist_info;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.oniononion.comp4521project.R;

/**
 * Created by ylcheung on 21/5/16.
 */
public class TouristWebViewActivity extends Activity {
    String url;
    WebView travelWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_information_webview);

        url= getIntent().getExtras().getString("url");

        travelWebView = (WebView)findViewById(R.id.travel_webview);
        travelWebView.setWebViewClient(new MyBrowser());

        travelWebView.getSettings().setJavaScriptEnabled(true);    // the website are using javascript
        travelWebView.getSettings().setLoadWithOverviewMode(true);
        travelWebView.getSettings().setBuiltInZoomControls(true);  // the webview zoom in/out

        if(URLUtil.isValidUrl(url)){
            travelWebView.loadUrl(url);
        }else{
            Toast.makeText(getApplicationContext(), "Website Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // This method specifies the WebView has a back history item
        if (travelWebView.canGoBack()) {
            travelWebView.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
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
