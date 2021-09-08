package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.AppViewModel;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationRequest;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.Repository.AppRepository;
import com.app.gadfixvendor.Utils.StringUtils;
import com.app.gadfixvendor.databinding.ActivityRegistrationBinding;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRegistrationBinding binding;
    float v=0;
    private static int SPLASH_TIME_OUT= 1000;
    private GadfixApiController gadfixApiController;
    private UserSharedpreference userSharedpreference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        gadfixApiController = new GadfixApiController(this);
        userSharedpreference = new UserSharedpreference(this);

        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.card2.setTranslationX(800);
        binding.card3.setTranslationX(800);
        binding.card4.setTranslationX(800);
        binding.card5.setTranslationX(800);
        binding.tv.setTranslationX(800);
        binding.btRegister.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.card2.setAlpha(v);
        binding.card3.setAlpha(v);
        binding.card4.setAlpha(v);
        binding.card5.setAlpha(v);
        binding.tv.setAlpha(v);
        binding.btRegister.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.card2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        binding.card3.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        binding.card4.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        binding.card5.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.tv.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.btRegister.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        binding.tvSign.setOnClickListener(v1 -> {
            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        });

        binding.btRegister.setOnClickListener(this);
        binding.lock.setOnClickListener(this);
        binding.lockOpen.setOnClickListener(this);
        binding.reLock.setOnClickListener(this);
        binding.reLockOpen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_register:
                if (binding.userName.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "please enter full name", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (binding.userEmail.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "please enter email id", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (!StringUtils.isEmailValid(binding.userEmail.getText().toString())){
                    Snackbar snackBar = Snackbar .make(v, "please enter valid email id", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (binding.userMobile.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "please enter mobile no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (binding.userPassword.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "please enter password", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (!StringUtils.isValidPassword(binding.userPassword.getText().toString())){
                    Snackbar snackBar = Snackbar .make(v, "please enter 8 character password", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (binding.userRePassword.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "please re enter password", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (!binding.userPassword.getText().toString().equals(binding.userRePassword.getText().toString())){
                    Snackbar snackBar = Snackbar .make(v, "please enter correct password", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.tvRegister.setVisibility(View.GONE);
                    binding.userName.setFocusableInTouchMode(false);
                    binding.userEmail.setFocusableInTouchMode(false);
                    binding.userMobile.setFocusableInTouchMode(false);
                    binding.userPassword.setFocusableInTouchMode(false);
                    binding.userRePassword.setFocusableInTouchMode(false);
                    binding.userReferalCode.setFocusableInTouchMode(false);
                    RegistrationRequest registrationRequest = new RegistrationRequest();
                    registrationRequest.setName(binding.userName.getText().toString());
                    registrationRequest.setEmail(binding.userEmail.getText().toString());
                    registrationRequest.setMno(binding.userMobile.getText().toString());
                    registrationRequest.setPassword(binding.userPassword.getText().toString());
                    registrationRequest.setReferal(binding.userReferalCode.getText().toString());
                    gadfixApiController.getRegistration(registrationRequest, new AppListener.OnUserRegisterListener() {
                        @Override
                        public void onSuccess(RegistrationResponse registrationResponse) {
                            Snackbar snackBar = Snackbar .make(v, registrationResponse.getMessage(), Snackbar.LENGTH_LONG);
                            snackBar.show();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvRegister.setVisibility(View.VISIBLE);
                            userSharedpreference.saveStringData(SharedPreferenceConfig.USER_ID, registrationResponse.getData().getUserId());
                            userSharedpreference.saveStringData(SharedPreferenceConfig.USER_MOBILE, registrationResponse.getData().getMobileNo());
                            new Handler().postDelayed(() -> {
                                startActivity(new Intent(RegistrationActivity.this,OtpVerificationActivity.class));
                                finish();
                                },SPLASH_TIME_OUT);

                        }

                        @Override
                        public void onFailure(String message) {
                            binding.userName.setFocusableInTouchMode(true);
                            binding.userEmail.setFocusableInTouchMode(true);
                            binding.userMobile.setFocusableInTouchMode(true);
                            binding.userPassword.setFocusableInTouchMode(true);
                            binding.userRePassword.setFocusableInTouchMode(true);
                            binding.userReferalCode.setFocusableInTouchMode(true);
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvRegister.setVisibility(View.VISIBLE);
                            Snackbar snackBar = Snackbar .make(v, message, Snackbar.LENGTH_LONG);
                            snackBar.show();
                        }
                    });

                }
                break;
            case R.id.lock:
                binding.userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.lockOpen.setVisibility(View.VISIBLE);
                binding.lock.setVisibility(View.GONE);
                break;
            case R.id.lock_open:
                binding.userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.lock.setVisibility(View.VISIBLE);
                binding.lockOpen.setVisibility(View.GONE);
                break;
            case R.id.re_lock:
                binding.userRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.reLockOpen.setVisibility(View.VISIBLE);
                binding.reLock.setVisibility(View.GONE);
                break;
            case R.id.re_lock_open:
                binding.userRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.reLock.setVisibility(View.VISIBLE);
                binding.reLockOpen.setVisibility(View.GONE);
                break;
        }
    }

}