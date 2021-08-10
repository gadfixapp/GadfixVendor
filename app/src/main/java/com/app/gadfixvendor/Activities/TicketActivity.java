package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAssignedNumbers;
import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityTicketBinding;

public class TicketActivity extends BaseActivity<ActivityTicketBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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