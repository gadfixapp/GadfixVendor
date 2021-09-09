package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.gadfixvendor.Dashboard.UserDashboardActivity;
import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsRequest;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.Utils.AppUtils;
import com.app.gadfixvendor.databinding.ActivityUserDetailsBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class UserDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityUserDetailsBinding binding;
    private File imgFile = null;
    private File imgFile1 = null;
    private File imgFile2 = null;
    private File imgFile3 = null;
    private File imgFile4 = null;
    private File imgFile5 = null;
    private int clickValue = 0;
    private int textValue = 0, textValue1 = 0, textValue2 = 0, textValue3 = 0, textValue4 = 0, textValue5 = 0;
    private GadfixApiController gadfixApiController;
    private UserSharedpreference userSharedpreference;
    private String latitude = "", longitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
        userSharedpreference = new UserSharedpreference(this);

        gadfixApiController = new GadfixApiController(this);

        binding.btRegister.setOnClickListener(v -> {
            startActivity(new Intent(UserDetailsActivity.this, HomeActivity.class));
        });

        //User Picture
        binding.userCard.setOnClickListener(v -> {
            clickValue = 1;
            textValue = 1;
//            if(checkAndRequestPermissions(UserDetailsActivity.this)){
//                userImage(UserDetailsActivity.this);
//            }
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
        binding.userPic.setOnClickListener(v -> {
            clickValue = 1;
            textValue = 1;
//            if(checkAndRequestPermissions(UserDetailsActivity.this)){
//                userImage(UserDetailsActivity.this);
//            }
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
        // User Shop Picture
        binding.userShopCard.setOnClickListener(v -> {
            clickValue = 2;
            textValue1 = 2;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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
        binding.userShopPic.setOnClickListener(v -> {
            clickValue = 2;
            textValue1 = 2;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        // User Font Aadhar Picture
        binding.userFontAadharCard.setOnClickListener(v -> {
            clickValue = 3;
            textValue2 = 3;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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
        binding.userFontAadharPic.setOnClickListener(v -> {
            clickValue = 3;
            textValue2 = 3;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        // User Back Aadhar Picture
        binding.userBackAadharCard.setOnClickListener(v -> {
            clickValue = 4;
            textValue3 = 4;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        binding.userBackAadharPic.setOnClickListener(v -> {
            clickValue = 4;
            textValue3 = 4;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        // User Font Pan Picture
        binding.userFontPanCard.setOnClickListener(v -> {
            clickValue = 5;
            textValue4 = 5;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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
        binding.userFontPanPic.setOnClickListener(v -> {
            clickValue = 5;
            textValue4 = 5;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        // User Back Pan Picture
        binding.userBackPanCard.setOnClickListener(v -> {
            clickValue = 6;
            textValue5 = 6;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        binding.userBackPanPic.setOnClickListener(v -> {
            clickValue = 6;
            textValue5 = 6;
//            if (checkAndRequestPermissions(UserDetailsActivity.this)) {
//                userImage(UserDetailsActivity.this);
//            }
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

        binding.btRegister.setOnClickListener(this);
        if (AppUtils.isGpsEnabled(getBaseContext())) {
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            Permissions.check(UserDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    SmartLocation.with(UserDetailsActivity.this).location()
                            .oneFix()
                            .start(new OnLocationUpdatedListener() {
                                @Override
                                public void onLocationUpdated(Location location) {
                                    latitude = String.valueOf(location.getLatitude());
                                    longitude = String.valueOf(location.getLongitude());
//                                    getData(longitude,latitude);
                                    Log.d("tuyuyew",longitude+"");
                                    Log.d("tuyuyew",latitude+"");
                                }
                            });
                }

                @Override
                public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                    super.onDenied(context, deniedPermissions);
//                    getData(longitude,latitude);
                }
            });
        } else {
//            getData(longitude,latitude);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("mPaths", mPaths.toString());
            if (clickValue == 1) {
                //Your Code
                binding.userPicCard.setVisibility(View.VISIBLE);
                binding.userCard.setVisibility(View.GONE);
                imgFile = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                binding.userPic.setImageBitmap(myBitmap);

            } else if (clickValue == 2) {
                binding.userShopCard.setVisibility(View.GONE);
                binding.userShop.setVisibility(View.VISIBLE);
                imgFile1 = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
                binding.userShopPic.setImageBitmap(myBitmap);
                Log.d("pathhhh", "onActivityResult: " + imgFile1);
            } else if (clickValue == 3) {
                binding.userFontAadharCard.setVisibility(View.GONE);
                binding.userFontAadhar.setVisibility(View.VISIBLE);
                imgFile2 = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
                binding.userFontAadharPic.setImageBitmap(myBitmap);
                Log.d("pathhhh", "onActivityResult: " + imgFile2);
            } else if (clickValue == 4) {
                binding.userBackAadharCard.setVisibility(View.GONE);
                binding.userBackAadhar.setVisibility(View.VISIBLE);
                imgFile3 = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile3.getAbsolutePath());
                binding.userBackAadharPic.setImageBitmap(myBitmap);
                Log.d("pathhhh", "onActivityResult: " + imgFile3);
            } else if (clickValue == 5) {
                binding.userFontPanCard.setVisibility(View.GONE);
                binding.userFontPan.setVisibility(View.VISIBLE);
                imgFile4 = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile4.getAbsolutePath());
                binding.userFontPanPic.setImageBitmap(myBitmap);
                Log.d("pathhhh", "onActivityResult: " + imgFile4);
            } else if (clickValue == 6) {
                binding.userBackPanCard.setVisibility(View.GONE);
                binding.userBackPan.setVisibility(View.VISIBLE);
                imgFile5 = new File(mPaths.get(0));
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile5.getAbsolutePath());
                binding.userBackPanPic.setImageBitmap(myBitmap);
                Log.d("pathhhh", "onActivityResult: " + imgFile5);
            }


        }

}


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                if (binding.shopName.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter shop name", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.licenceNo.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter licence no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue != 1) {
                    Snackbar snackBar = Snackbar.make(v, "upload your pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue1 != 2) {
                    Snackbar snackBar = Snackbar.make(v, "upload your shop pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.since.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter since date", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.registrationNo.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter registration no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.gstNo.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter GST no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.address.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter address", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.pinCode.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter pin code", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.landmark.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter landmark", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.timing.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter shop timing", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.noOfEmployee.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter no of employee", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.aadharNo.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter aadhar no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue2 != 3) {
                    Snackbar snackBar = Snackbar.make(v, "upload your font aadhar pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue3 != 4) {
                    Snackbar snackBar = Snackbar.make(v, "upload your  back aadhar pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (binding.panNo.getText().toString().trim().equals("")) {
                    Snackbar snackBar = Snackbar.make(v, "enter pan no", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue4 != 5) {
                    Snackbar snackBar = Snackbar.make(v, "upload your font pan pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else if (textValue5 != 6) {
                    Snackbar snackBar = Snackbar.make(v, "upload your back pan pic", Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.tvSubmit.setVisibility(View.GONE);
                    UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
                    userDetailsRequest.setShopName(binding.shopName.getText().toString());
                    userDetailsRequest.setLicenseNumber(binding.licenceNo.getText().toString());
                    userDetailsRequest.setSince(binding.since.getText().toString());
                    userDetailsRequest.setRegistrationNo(binding.registrationNo.getText().toString());
                    userDetailsRequest.setGstNo(binding.gstNo.getText().toString());
                    userDetailsRequest.setAddress(binding.address.getText().toString());
                    userDetailsRequest.setPinCode(binding.pinCode.getText().toString());
                    userDetailsRequest.setLandmark(binding.landmark.getText().toString());
                    userDetailsRequest.setTiming(binding.timing.getText().toString());
                    userDetailsRequest.setNoOfEmployee(binding.noOfEmployee.getText().toString());
                    userDetailsRequest.setAadharNo(binding.aadharNo.getText().toString());
                    userDetailsRequest.setPanNo(binding.panNo.getText().toString());
                    userDetailsRequest.setLongitude(longitude);
                    userDetailsRequest.setLatitude(latitude);
                    userDetailsRequest.setUserPic(imgFile);
                    userDetailsRequest.setUserShopPic(imgFile1);
                    userDetailsRequest.setUserFontAadhar(imgFile2);
                    userDetailsRequest.setUserBackAadhar(imgFile3);
                    userDetailsRequest.setUserFontPan(imgFile4);
                    userDetailsRequest.setUserBackPan(imgFile5);
                    gadfixApiController.getUserDetails(userDetailsRequest, new AppListener.OnUserDetailsListener() {
                        @Override
                        public void onSuccess(UserDetailsResponse userDetailsResponse) {
                            if (userDetailsResponse.getResponseCode() == 200) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvSubmit.setVisibility(View.VISIBLE);
                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                // write all the data entered by the user in SharedPreference and apply
                                myEdit.putString("true","Success");
                                myEdit.apply();
                                Snackbar snackBar = Snackbar.make(v, userDetailsResponse.getMessage(), Snackbar.LENGTH_LONG);
                                snackBar.show();
                                startActivity(new Intent(UserDetailsActivity.this,HomeActivity.class));
                            } else {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.tvSubmit.setVisibility(View.VISIBLE);
                                Snackbar snackBar = Snackbar.make(v, userDetailsResponse.getMessage(), Snackbar.LENGTH_LONG);
                                snackBar.show();
                            }
                        }

                        @Override
                        public void onFailure(String message) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.tvSubmit.setVisibility(View.VISIBLE);
                            Snackbar snackBar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
                            snackBar.show();
                        }
                    });

                }
        }

    }
}