package com.example.wholeweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView browser;
    SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browser = findViewById(R.id.webview);
        refresh = findViewById(R.id.refresh);

        browser.setWebViewClient(new Browser());
        loadUrl("https://wholeweb.onrender.com/");

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    public void loadUrl (String url) {
        browser.loadUrl(url);
    }

    private class Browser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void refreshContent () {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String url = browser.getUrl();
                loadUrl(url);
                refresh.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        if(browser.canGoBack()) {
            browser.goBack();
        } else super.onBackPressed();
    }
}