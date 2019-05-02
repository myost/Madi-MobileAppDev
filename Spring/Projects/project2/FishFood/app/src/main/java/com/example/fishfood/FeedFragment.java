package com.example.fishfood;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {
    private LocalDateTime now;
    private LocalDateTime nextFeed;
    private Time time;
    private Button feedButton;
    private TextView timeRemainingView;
    private MainActivity activity;
    private CountDownTimer countDownTimer;
    private static long TWELVE_HOURS_MILLIS = 43200000;
    private static DecimalFormat formatter = new DecimalFormat("00");



    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        feedButton = v.findViewById(R.id.feedButton);
        timeRemainingView = v.findViewById(R.id.timeRemainingTextView);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer != null){
                    countDownTimer.cancel();
                }
                now = LocalDateTime.now();
                nextFeed = now.plusHours(12);
                //nextFeed = now.plusSeconds(10);
                activity.realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Time updateTime = realm.where(Time.class).equalTo("id", "timer").findFirst();
                        updateTime.setLastFed(Date.from(nextFeed.atZone(ZoneId.systemDefault()).toInstant()));
                    }
                });
                updateTimer(nextFeed);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        time = activity.realm.where(Time.class).equalTo("id", "timer").findFirst();
        //Log.i("Timer instance", String.valueOf(time));
        now = LocalDateTime.now();
        if(time != null){
            Date timeLastFed = time.getLastFed();
            nextFeed = Instant.ofEpochMilli(timeLastFed.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            updateTimer(nextFeed);
        }else{
            nextFeed = now.plusHours(12);
            //nextFeed = now.plusSeconds(10);
            activity.realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    time = realm.createObject(Time.class, "timer");
                    //Log.i("Timer instance", String.valueOf(time));
                    time.setLastFed(Date.from(nextFeed.atZone(ZoneId.systemDefault()).toInstant()));
                }
            });
            updateTimer(nextFeed);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
        activity.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Time updateTime = realm.where(Time.class).equalTo("id", "timer").findFirst();
                updateTime.setLastFed(Date.from(nextFeed.atZone(ZoneId.systemDefault()).toInstant()));
            }
        });
    }

    private void createTimeUpDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.feed_fish_dialog);
        dialog.setPositiveButton("Feed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextFeed = LocalDateTime.now().plusHours(12);
                //nextFeed = LocalDateTime.now().plusSeconds(10);
                activity.realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Time updateTime = realm.where(Time.class).equalTo("id", "timer").findFirst();
                        updateTime.setLastFed(Date.from(nextFeed.atZone(ZoneId.systemDefault()).toInstant()));
                    }
                });
                restartTimer(TWELVE_HOURS_MILLIS);
            }
        });
        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }

    private void updateTimer(LocalDateTime feedNext){
        long timeDiff = getDifference(now, feedNext);
        //Log.i("inside update function", String.valueOf(timeDiff));
        if(timeDiff < 0){
            createTimeUpDialog();
        }else{
            restartTimer(timeDiff);
        }
    }

    private void restartTimer(long duration){
        countDownTimer = new CountDownTimer(duration, 1000){
            long hrsLeft;
            long minsLeft;
            long secsLeft;
            @Override
            public void onTick(long millisUntilFinished) {
                hrsLeft = millisUntilFinished / 3600000;
                minsLeft = (millisUntilFinished % 3600000) / 60000;
                secsLeft = ((millisUntilFinished % 3600000) % 60000) / 1000;
                setTextField(hrsLeft, minsLeft, secsLeft);
            }
            @Override
            public void onFinish() {
                createTimeUpDialog();
            }
        }.start();
    }

    private void setTextField(long hours, long mins, long secs){
        String timeLeftString = formatter.format(hours)+":"+formatter.format(mins)+":"+formatter.format(secs);
        timeRemainingView.setText(timeLeftString);
    }

    private long getDifference(LocalDateTime start, LocalDateTime end){
        return start.until(end, ChronoUnit.MILLIS);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", "Feed");
        outState.putInt("selectedItemId", R.id.navigation_feed);
    }
}
