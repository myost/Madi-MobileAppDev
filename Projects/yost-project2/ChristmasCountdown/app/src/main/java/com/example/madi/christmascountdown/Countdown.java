package com.example.madi.christmascountdown;

import android.os.CountDownTimer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Countdown {
    private long timeToXmas;
    private LocalDateTime today;
    private LocalDateTime christmas;
    private TextView daysView;
    private TextView hoursView;
    private TextView minsView;
    private TextView secsView;

    private long getDifference(LocalDateTime start, LocalDateTime end){
        return start.until(end, ChronoUnit.MILLIS);
    }

    private void setTextView(String tag, TextView textView){
        switch(tag){
            case "days":
                daysView = textView;
                break;
            case "hours":
                hoursView = textView;
                break;
            case "mins":
                minsView = textView;
                break;
            case "secs":
                secsView = textView;
                break;
            default:
                break;
        }
    }

    private void setTextFields(long days, long hours, long mins, long secs){
        daysView.setText(String.valueOf(days));
        hoursView.setText(String.valueOf(hours));
        minsView.setText(String.valueOf(mins));
        secsView.setText(String.valueOf(secs));
    }

    public void setDates(){
        today = LocalDateTime.now();
        int year = today.getYear();
        //error catch for if it is after december 24th and not yet January 1st of the next year
        if(today.getMonth() == Month.DECEMBER && today.getDayOfMonth()>24){
            year = year+1;
        }
        christmas = LocalDateTime.of(year, 12, 25, 0, 0, 0);
    }

    public void setDaysView(TextView textView){
        setTextView("days", textView);
    }

    public void setHoursView(TextView textView){
        setTextView("hours", textView);
    }

    public void setMinsView(TextView textView){
        setTextView("mins", textView);
    }

    public void setSecsView(TextView textView){
        setTextView("secs", textView);
    }

    public void setTimer(){
        timeToXmas = getDifference(today, christmas);
        new CountDownTimer(timeToXmas, 1000) {
            long daysToXmas;
            long hrsToXmas;
            long minsToXmas;
            long secsToXmas;
            @Override
            public void onTick(long millisUntilFinished) {
                daysToXmas = millisUntilFinished / 86400000;
                hrsToXmas = (millisUntilFinished % 86400000) / 3600000;
                minsToXmas = ((millisUntilFinished % 86400000) % 3600000) / 60000;
                secsToXmas = (((millisUntilFinished % 86400000) % 3600000) % 60000) / 1000;
                setTextFields(daysToXmas, hrsToXmas, minsToXmas, secsToXmas);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

}
