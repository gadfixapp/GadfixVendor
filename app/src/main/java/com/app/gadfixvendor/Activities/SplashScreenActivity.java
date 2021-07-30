package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.app.gadfixvendor.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(SplashScreenActivity.this, IntroActivity.class);
            startActivity(homeIntent);
            finish();

        },SPLASH_TIME_OUT);
    }
}