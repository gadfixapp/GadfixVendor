package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityNotificationBinding;

public class NotificationActivity extends BaseActivity<ActivityNotificationBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //content view
    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification;
    }

    //navigation view
    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}