package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash_screen);

        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent (Splash_Screen.this, MainActivity.class);
                startActivity (intent);
                finish ();
            }
        }, SPLASH_TIME_OUT);
    }
}
