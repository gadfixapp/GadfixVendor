package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.tvForgotPassword.setTranslationX(800);
        binding.login.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.tvForgotPassword.setAlpha(v);
        binding.login.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.tvForgotPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        binding.tvForgotPassword.setOnClickListener(v1 -> {
            startActivity(new Intent(this,ForgotPasswordActivity.class));
        });
    }
}