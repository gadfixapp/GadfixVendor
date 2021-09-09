package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.Utils.StringUtils;
import com.app.gadfixvendor.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {
    private UserSharedpreference userSharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSharedpreference = new UserSharedpreference(this);

         binding.contentDashBoard.logout.setOnClickListener(v -> {
             new AlertDialog.Builder(ProfileActivity.this)
                     .setMessage("Are you sure want to logout?")
                     .setCancelable(true)
                     .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                             userSharedpreference.saveBooleanData(SharedPreferenceConfig.USER_DETAILS,true);
                             StringUtils.deleteSharedPreferenceDataForlogout(ProfileActivity.this);
                             StringUtils.launchActivity(ProfileActivity.this, LoginActivity.class);
                             finish();
                         }
                     })
                     .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                         }
                     }).show();
         });


    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }
}