package com.example.madi.christmascountdown;

import android.widget.TextView;

public class Quote {
    private String[] quoteArray;
    private TextView quoteView;

    public void setQuoteView(TextView textView){
        quoteView = textView;
    }

    public void setQuoteArray(String[] array){
        quoteArray = array;
    }

    public void setQuote(){
        int i = (int) Math.floor(Math.random()*30);
        String quote = quoteArray[i];
        quoteView.setText(quote);
    }

    public void setSavedQuote(String quote){
        quoteView.setText(quote);
    }

    public String getQuote(){
        return (String)quoteView.getText();
    }

}
