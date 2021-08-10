package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityPaymentBinding;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_payment;
    }
}