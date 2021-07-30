package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ActivityForgotPasswordBinding binding;
    private float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.card2.setTranslationX(800);
        binding.verify.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.card2.setAlpha(v);
        binding.verify.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.card2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.verify.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
    }
}