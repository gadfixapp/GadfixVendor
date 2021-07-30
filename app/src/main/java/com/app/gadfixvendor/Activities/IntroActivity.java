package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_intro);

        binding.tvSign.setOnClickListener(v -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });

        binding.btRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this,RegistrationActivity.class);
            startActivity(intent);
        });

    }
}