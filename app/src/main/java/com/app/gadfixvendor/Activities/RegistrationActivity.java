package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity{
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);

        binding.card.setTranslationX(800);
        binding.card1.setTranslationX(800);
        binding.card2.setTranslationX(800);
        binding.card3.setTranslationX(800);
        binding.card4.setTranslationX(800);
        binding.card5.setTranslationX(800);
        binding.tv.setTranslationX(800);
        binding.login.setTranslationX(800);

        binding.card.setAlpha(v);
        binding.card1.setAlpha(v);
        binding.card2.setAlpha(v);
        binding.card3.setAlpha(v);
        binding.card4.setAlpha(v);
        binding.card5.setAlpha(v);
        binding.tv.setAlpha(v);
        binding.login.setAlpha(v);

        binding.card.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.card1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.card2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        binding.card3.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        binding.card4.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        binding.card5.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.tv.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        binding.tvSign.setOnClickListener(v1 -> {
            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        });

        binding.btRegister.setOnClickListener(v1 -> {
            startActivity(new Intent(RegistrationActivity.this,OtpVerificationActivity.class));

        });

    }


}