package com.example.madi.christmascountdown;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class CountdownFragment extends Fragment {
    private static final String TAG="CountdownFragment";
    private Countdown xmasCountdown = new Countdown();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countdown_fragment,container,false);
        //set up the timer instance by setting todays date and christmas this year
        xmasCountdown.setDates();
        TextView daysView = view.findViewById(R.id.days);
        xmasCountdown.setDaysView(daysView);
        TextView hoursView = view.findViewById(R.id.hours);
        xmasCountdown.setHoursView(hoursView);
        TextView minsView = view.findViewById(R.id.minutes);
        xmasCountdown.setMinsView(minsView);
        TextView secsView = view.findViewById(R.id.seconds);
        xmasCountdown.setSecsView(secsView);
        xmasCountdown.setTimer();
        return view;
    }
}
