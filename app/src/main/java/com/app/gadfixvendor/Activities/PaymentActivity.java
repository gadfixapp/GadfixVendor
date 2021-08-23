package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

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
}