package pl.edu.copernicuscenter.ccrecstopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    private Button startPauseButton;
    private ImageView imageView;
    private AnimatedVectorDrawableCompat avdComp;
    private AnimatedVectorDrawable avd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        startPauseButton = findViewById(R.id.playPauseButton);

        imageView = findViewById(R.id.imagePlayPause);


        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                if (running) {

                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.pausetoplay));
                    Drawable drawable = imageView.getDrawable();

                    if (drawable instanceof AnimatedVectorDrawableCompat) {
                        avdComp = (AnimatedVectorDrawableCompat) drawable;
                        avdComp.start();
                    } else if (drawable instanceof AnimatedVectorDrawable) {
                        avd = (AnimatedVectorDrawable) drawable;
                        avd.start();
                    }

                    pauseChronometer(view);
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.playtopause));
                    Drawable drawable = imageView.getDrawable();

                    if (drawable instanceof AnimatedVectorDrawableCompat) {
                        avdComp = (AnimatedVectorDrawableCompat) drawable;
                        avdComp.start();
                    } else if (drawable instanceof AnimatedVectorDrawable) {
                        avd = (AnimatedVectorDrawable) drawable;
                        avd.start();
                    }

                    startChronometer(view);
                }
            }
        });
    }


    public void startChronometer (View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer (View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer (View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}