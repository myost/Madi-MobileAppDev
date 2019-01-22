package com.example.madi.christmascountdown;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class QuotesFragment extends Fragment {
    private static final String TAG="QuotesFragment";
    private ImageButton quoteBtn;
    private Quote myQuote = new Quote();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quotes_fragment,container,false);
        myQuote.setQuoteArray(getResources().getStringArray(R.array.quotes));
        myQuote.setQuoteView((TextView)view.findViewById(R.id.quote));
        if(savedInstanceState != null){
            String quote = savedInstanceState.getString("quote");
            myQuote.setSavedQuote(quote);
        }else{
            myQuote.setQuote();
        }
        quoteBtn = view.findViewById(R.id.imageButton);
        quoteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                myQuote.setQuote();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("quote", myQuote.getQuote());
    }
}
