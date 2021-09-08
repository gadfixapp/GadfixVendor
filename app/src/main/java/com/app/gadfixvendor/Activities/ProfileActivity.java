package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.Utils.StringUtils;
import com.app.gadfixvendor.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding.contentDashBoard.logout.setOnClickListener(v -> {
             new AlertDialog.Builder(ProfileActivity.this)
                     .setMessage("Are you sure want to logout?")
                     .setCancelable(true)
                     .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }
}