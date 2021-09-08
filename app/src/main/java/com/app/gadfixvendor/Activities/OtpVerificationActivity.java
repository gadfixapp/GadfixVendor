package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.OtpModel.OtpRequest;
import com.app.gadfixvendor.Models.OtpModel.OtpResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityOtpVerificationBinding;
import com.google.android.material.snackbar.Snackbar;

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityOtpVerificationBinding binding;
    private UserSharedpreference userSharedpreference;
    private String mobile_no;
    private GadfixApiController gadfixApiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verification);
        userSharedpreference = new UserSharedpreference(this);
        gadfixApiController = new GadfixApiController(this);
        mobile_no = userSharedpreference.getStringData(SharedPreferenceConfig.USER_MOBILE);
        Log.d("mobileeee", "onCreate: "+mobile_no);


        binding.btVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_verify:
                if (binding.pinview.getValue().equals("")){
                    Snackbar snackbar = Snackbar.make(v,"enter otp", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.tvOtp.setVisibility(View.GONE);
                    OtpRequest otpRequest = new OtpRequest();
                    otpRequest.setMno(mobile_no);
                    otpRequest.setOtp(binding.pinview.getValue());
                    gadfixApiController.getOtpVerify(otpRequest, new AppListener.OnUserOtpListener() {
                        @Override
                        public void onSuccess(OtpResponse otpResponse) {
                           if (otpResponse.getResponseCode()==200){
                               binding.progressBar.setVisibility(View.GONE);
                               binding.tvOtp.setVisibility(View.VISIBLE);
                               Snackbar snackbar = Snackbar.make(v,otpResponse.getMessage(),Snackbar.LENGTH_LONG);
                               snackbar.show();
                               startActivity(new Intent(OtpVerificationActivity.this, UserDetailsActivity.class));
                           }
                        }

                        @Override
                        public void onFailure(String message) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvOtp.setVisibility(View.VISIBLE);
                            Snackbar snackbar = Snackbar.make(v,message,Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    });
                }
        }
    }
}