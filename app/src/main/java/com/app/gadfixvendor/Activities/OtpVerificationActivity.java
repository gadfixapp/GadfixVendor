package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityOtpVerificationBinding;

public class OtpVerificationActivity extends AppCompatActivity {
    private ActivityOtpVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verification);

        binding.btVerify.setOnClickListener(v -> {
            startActivity(new Intent(this,UserDetailsActivity.class));
        });
    }
}