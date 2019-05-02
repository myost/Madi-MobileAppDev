package com.example.fishfood;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    private ProgressBar progressBar;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shop, container, false);
        progressBar = rootView.findViewById(R.id.progressBar);
        WebView webView = rootView.findViewById(R.id.fishShopView);
        webView.setWebViewClient(new ShopWebViewClient(progressBar));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.petsmart.com/fish/live-fish/goldfish-betta-and-more/guppies/");
        return rootView;
    }

    private class ShopWebViewClient extends WebViewClient{
        private ProgressBar progressBar;

        public ShopWebViewClient(ProgressBar progressBar){
            this.progressBar = progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", "PetSmart");
        outState.putInt("selectedItemId", R.id.navigation_shop);
    }

}
