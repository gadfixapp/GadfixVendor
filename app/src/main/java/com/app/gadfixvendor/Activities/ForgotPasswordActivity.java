package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordRequest;
import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityForgotPasswordBinding;
import com.google.android.material.snackbar.Snackbar;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityForgotPasswordBinding binding;
    private float v=0;
    private static int SPLASH_TIME_OUT= 1000;
    private GadfixApiController gadfixApiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        gadfixApiController = new GadfixApiController(this);
        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.card2.setTranslationX(800);
        binding.btVerify.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.card2.setAlpha(v);
        binding.btVerify.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.card2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.btVerify.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        binding.btVerify.setOnClickListener(this);
        binding.newLock.setOnClickListener(this);
        binding.newLockOpen.setOnClickListener(this);
        binding.confirmLock.setOnClickListener(this);
        binding.confirmLockOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_verify:
                if (binding.userMobile.getText().toString().trim().equals("")){
                    Snackbar snackbar = Snackbar.make(v,"enter your mobile no",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (binding.userMobile.getText().toString().length()<10){
                    Snackbar snackbar = Snackbar.make(v,"enter 10 digit mobile no",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (binding.userNewPassword.getText().toString().trim().equals("")){
                    Snackbar snackbar = Snackbar.make(v,"enter new password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (binding.userConfirmPassword.getText().toString().trim().equals("")){
                    Snackbar snackbar = Snackbar.make(v,"enter confirm password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (!binding.userNewPassword.getText().toString().equals(binding.userConfirmPassword.getText().toString())){
                    Snackbar snackbar = Snackbar.make(v,"enter valid password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.tvForgot.setVisibility(View.GONE);
                    binding.userMobile.setFocusableInTouchMode(false);
                    binding.userNewPassword.setFocusableInTouchMode(false);
                    binding.userConfirmPassword.setFocusableInTouchMode(false);
                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
                    changePasswordRequest.setMno(binding.userMobile.getText().toString());
                    changePasswordRequest.setPassword(binding.userNewPassword.getText().toString());
                    gadfixApiController.getChangePassword(changePasswordRequest, new AppListener.OnUserChangePasswordListener() {
                        @Override
                        public void onSuccess(ChangePasswordResponse changePasswordResponse) {
                            if (changePasswordResponse.getResponseCode() == 200){
                                Snackbar snackbar = Snackbar.make(v,changePasswordResponse.getMessage(),Snackbar.LENGTH_LONG);
                                snackbar.show();
                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvForgot.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(() -> {
                                    startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                                    finish();

                                },SPLASH_TIME_OUT);

                            }else if (changePasswordResponse.getResponseCode() == 201){
                                binding.userMobile.setFocusableInTouchMode(true);
                                binding.userNewPassword.setFocusableInTouchMode(true);
                                binding.userConfirmPassword.setFocusableInTouchMode(true);
                                Snackbar snackbar = Snackbar.make(v,changePasswordResponse.getMessage(),Snackbar.LENGTH_LONG);
                                snackbar.show();
                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvForgot.setVisibility(View.VISIBLE);

                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            binding.userMobile.setFocusableInTouchMode(true);
                            binding.userNewPassword.setFocusableInTouchMode(true);
                            binding.userConfirmPassword.setFocusableInTouchMode(true);
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvForgot.setVisibility(View.VISIBLE);
                            Snackbar snackBar = Snackbar .make(v, message, Snackbar.LENGTH_LONG);
                            snackBar.show();
                        }
                    });

                }
                break;

            case R.id.new_lock:
                binding.userNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.newLockOpen.setVisibility(View.VISIBLE);
                binding.newLock.setVisibility(View.GONE);
                break;
            case R.id.new_lock_open:
                binding.userNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.newLock.setVisibility(View.VISIBLE);
                binding.newLockOpen.setVisibility(View.GONE);
                break;
            case R.id.confirm_lock:
                binding.userConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.confirmLockOpen.setVisibility(View.VISIBLE);
                binding.confirmLock.setVisibility(View.GONE);
                break;
            case R.id.confirm_lock_open:
                binding.userConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.confirmLock.setVisibility(View.VISIBLE);
                binding.confirmLockOpen.setVisibility(View.GONE);
                break;
        }
    }
}