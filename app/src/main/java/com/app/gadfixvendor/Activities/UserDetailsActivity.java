package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.gadfixvendor.Dashboard.UserDashboardActivity;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityUserDetailsBinding;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_details);

        binding.btRegister.setOnClickListener(v -> {
            startActivity(new Intent(UserDetailsActivity.this, HomeActivity.class));
        });

    }
}