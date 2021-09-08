package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.Utils.StringUtils;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT= 3000;
    private UserSharedpreference userSharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        userSharedpreference = new UserSharedpreference(this);
        userSharedpreference = userSharedpreference.getInstance(getBaseContext());
        userSharedpreference.saveStringData(SharedPreferenceConfig.DEVICE_ID, StringUtils.getDeviceId(getBaseContext()));
        Log.d("deviceiddd", "onCreate: "+ userSharedpreference.getStringData(SharedPreferenceConfig.DEVICE_ID));
        if (!userSharedpreference.getBooleanData(SharedPreferenceConfig.IS_FIRST_TIME_LAUNCH)) {
            userSharedpreference.saveBooleanData(SharedPreferenceConfig.IS_FIRST_TIME_LAUNCH, true);
        }
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    UserSharedpreference userDetailsPrefrennce = userSharedpreference.getInstance(getBaseContext());
                    if (userDetailsPrefrennce.getBooleanData(SharedPreferenceConfig.IS_USER_LOGIN)) {
                        Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, IntroActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}