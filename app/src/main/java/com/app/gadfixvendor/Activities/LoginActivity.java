package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.LoginModel.LoginRequest;
import com.app.gadfixvendor.Models.LoginModel.LoginResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT= 3000;
    private ActivityLoginBinding binding;
    float v=0;
    private GadfixApiController gadfixApiController;
    private UserSharedpreference userSharedpreference;
    private Boolean value = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        userSharedpreference = new UserSharedpreference(this);

        gadfixApiController = new GadfixApiController(this);
        value = userSharedpreference.getBooleanData(SharedPreferenceConfig.USER_DETAILS);
        Log.d("hsfkbskdfb", "onCreate: "+value);

        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.tvForgotPassword.setTranslationX(800);
        binding.btLogin.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.tvForgotPassword.setAlpha(v);
        binding.btLogin.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.tvForgotPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.btLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

       binding.btLogin.setOnClickListener(this);
       binding.lock.setOnClickListener(this);
       binding.lockOpen.setOnClickListener(this);
       binding.tvForgotPassword.setOnClickListener(v1 -> {
           startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
       });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                if (binding.userMobile.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "enter your mobile no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if(binding.userMobile.getText().toString().length() < 10){
                    Snackbar snackBar = Snackbar .make(v, "enter 10 digit mobile no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else if (binding.userPassword.getText().toString().trim().equals("")){
                    Snackbar snackBar = Snackbar .make(v, "enter your password", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.tvLogin.setVisibility(View.GONE);
                    binding.userMobile.setFocusableInTouchMode(false);
                    binding.userPassword.setFocusableInTouchMode(false);
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setMno(binding.userMobile.getText().toString());
                    loginRequest.setPassword(binding.userPassword.getText().toString());
                    gadfixApiController.getLogin(loginRequest, new AppListener.OnUserLoginListener() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            Log.d("codeeeee", "onSuccess: "+ loginResponse.getResponseCode());
                            if (loginResponse.getResponseCode() == 200){
                                userSharedpreference.saveStringData(SharedPreferenceConfig.USER_ID,loginResponse.getData().getUserId());
                                if (value){
                                    startActivity(new Intent(LoginActivity.this, UserDetailsActivity.class));
                                    finish();
                                }else if (!value){
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();
                                }

                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvLogin.setVisibility(View.VISIBLE);

                            }else if(loginResponse.getResponseCode() == 201){
                                Snackbar snackBar = Snackbar .make(v, loginResponse.getMessage(), Snackbar.LENGTH_LONG);
                                snackBar.show();
                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvLogin.setVisibility(View.VISIBLE);
                                startActivity(new Intent(LoginActivity.this, OtpVerificationActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            binding.userMobile.setFocusableInTouchMode(true);
                            binding.userPassword.setFocusableInTouchMode(true);
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvLogin.setVisibility(View.VISIBLE);
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
        }

    }
}