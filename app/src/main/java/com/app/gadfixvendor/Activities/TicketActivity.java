package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAssignedNumbers;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityTicketBinding;

public class TicketActivity extends BaseActivity<ActivityTicketBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_tickets;
    }
}