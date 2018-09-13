package com.hfad.workout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StopwatchFragment extends Fragment implements View.OnClickListener {
    private boolean running = false;
    private int seconds = 0;
    private boolean wasRunning = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running");
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View layout =inflater.inflate(R.layout.fragment_stopwatch, container, false);
       runTimer(layout);

        Button startbutton = (Button) layout.findViewById(R.id.bStart);
        startbutton.setOnClickListener(this);
        Button stopbutton = (Button) layout.findViewById(R.id.bStop);
        stopbutton.setOnClickListener(this);
        Button resetbutton = (Button) layout.findViewById(R.id.bReset);
        resetbutton.setOnClickListener(this);

       return layout;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
        //  System.out.println("Стала видимой для пользователя");
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
        //   System.out.println("Скрылся");
    }

    public void onClickStartb(View view) {
        running = true;
     //   CharSequence startclicktoast = "Собщение-уведомление (Таймер запущен)";
      //  int toastsequencestart = Toast.LENGTH_SHORT;
      //  Toast toast = Toast.makeText(this, startclicktoast, toastsequencestart);
       // toast.show();
    }

    public void onClickStopb(View view) {
        running = false;
    }

    public void onClickResetb(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                    //    System.out.println("still running");
                } //else System.out.println("waiting for start timer");
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStart: onClickStartb(v); break;
            case R.id.bStop: onClickStopb(v); break;
            case R.id.bReset: onClickResetb(v); break;
        }
    }
}
