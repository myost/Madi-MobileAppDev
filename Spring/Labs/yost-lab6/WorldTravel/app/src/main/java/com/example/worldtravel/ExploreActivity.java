package com.example.worldtravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.app.ActionBar;
import android.webkit.WebView;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        //get the toolbar and set it as app bar
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        //enable the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set up the web view
        WebView browser = (WebView) findViewById(R.id.webView);
        browser.loadUrl("https://www.lonelyplanet.com/");
//        browser.canGoBack();
//        browser.canGoForward();
    }
}
