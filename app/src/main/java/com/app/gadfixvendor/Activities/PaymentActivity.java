package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.app.gadfixvendor.Adapters.PaymentAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityPaymentBinding;


public class PaymentActivity extends BaseActivity<ActivityPaymentBinding> implements PaymentAdapter.Onclick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.contentDashBoard.recyclerView.setHasFixedSize(true);
        binding.contentDashBoard.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.contentDashBoard.recyclerView.setAdapter(new PaymentAdapter(this,PaymentActivity.this));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_payment;
    }

    @Override
    public void getPosition(int position) {
        Log.d("card_position", "getPosition: "+position);
    }
}