package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start_button);
        start.setOnClickListener(v -> running = true);
        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });*/
        runTimer();
    }
    public void onClickStop(View view) {
        running = false;
    }
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        /* For my understanding:
            Handler to schedule the stopwatch code to run every second.
            To use the Handler, you wrap the code you wish to schedule in
            a Runnable object, and then use the Handler post() and
            postDelayed() methods to specify when you want the code to run.
            Let’s take a closer look at these mehods

            The post() method posts code that needs to be run as soon as
            possible (which is usually almost immediately). This method takes
            one parameter, an object of type Runnable. A Runnable object in
            Androidville is just like a Runnable in plain old Java: a job you want
            to run. You put the code you want to run in the Runnable’s run()
            method, and the Handler will make sure the code is run as soon as possible
        * */
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}