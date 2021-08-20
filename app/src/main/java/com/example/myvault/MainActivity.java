package com.example.myvault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MediaPlayer morningWish, afternoonWish, eveningWish;
    TextView tvA1, tvS1, tvS2, tvA2, tvS3, tvS4, tvI, tvN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        morningWish = MediaPlayer.create(this, R.raw.good_morning);
        afternoonWish = MediaPlayer.create(this, R.raw.good_afternoon);
        eveningWish = MediaPlayer.create(this, R.raw.good_evening);

        setContentView(R.layout.activity_main);

        String currentDate = new SimpleDateFormat("hh:mm a").format(new Date());

        String amPm;
        int hour;

        hour = Integer.valueOf(currentDate.substring(0, 2));
        amPm = currentDate.substring(6, 8);

        if (hour >= 5 && hour <= 11 && amPm.equals("am"))
            morningWish.start();
        else if ((hour == 12 || hour <= 5) && amPm.equals("pm"))
            afternoonWish.start();
        else
            eveningWish.start();

        tvA1 = (TextView) findViewById(R.id.textViewA1);
        tvS1 = (TextView) findViewById(R.id.textViewS1);
        tvS2 = (TextView) findViewById(R.id.textViewS2);
        tvA2 = (TextView) findViewById(R.id.textViewA2);
        tvS3 = (TextView) findViewById(R.id.textViewS3);
        tvS4 = (TextView) findViewById(R.id.textViewS4);
        tvI = (TextView) findViewById(R.id.textViewI);
        tvN = (TextView) findViewById(R.id.textViewN);

        int del = 2000, gap=200;
        for(int i=0;i<100;i++) {
            blinkingLetter(tvA1, del);
            del += gap;
            blinkingLetter(tvS1, del);
            del += gap;
            blinkingLetter(tvS2, del);
            del += gap;
            blinkingLetter(tvA2, del);
            del += gap;
            blinkingLetter(tvS3, del);
            del += gap;
            blinkingLetter(tvS4, del);
            del += gap;
            blinkingLetter(tvI, del);
            del += gap;
            blinkingLetter(tvN, del);
            del += gap;
        }

    }

    public void blinkingLetter(TextView v, int delay) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.startAnimation(animation);
            }
        }, delay);
    }

    public void diveIn(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        this.startActivity(intent);
        finish();
    }
}