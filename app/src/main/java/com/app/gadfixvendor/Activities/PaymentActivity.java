package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        showdialogbox();
    }

    void showdialogbox(){
        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.payment_dialogue);
        dialog.show();
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

}