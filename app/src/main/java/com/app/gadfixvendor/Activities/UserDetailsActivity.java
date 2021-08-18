package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.gadfixvendor.Dashboard.UserDashboardActivity;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityUserDetailsBinding;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;
    private File imgFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_details);

        binding.btRegister.setOnClickListener(v -> {
            startActivity(new Intent(UserDetailsActivity.this, HomeActivity.class));
        });

        binding.clickCard.setOnClickListener(v -> {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            Permissions.check(UserDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    new ImagePicker.Builder(UserDetailsActivity.this)
                            .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                            .compressLevel(ImagePicker.ComperesLevel.NONE)
                            .directory(ImagePicker.Directory.DEFAULT)
                            .extension(ImagePicker.Extension.JPG)
                            .scale(600, 600)
                            .allowMultipleImages(false)
                            .enableDebuggingMode(true)
                            .build();
                }
                @Override
                public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                    super.onDenied(context, deniedPermissions);
                }
            });
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("imagedata", "onActivityResult: "+ data);
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null ) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("mPaths", mPaths.toString());
            //Your Code
            binding.userCard.setVisibility(View.VISIBLE);
            binding.clickCard.setVisibility(View.GONE);
            imgFile = new File(mPaths.get(0));
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            binding.userPic.setImageBitmap(myBitmap);
        }

    }
}