package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.ServiceUploadModel.ServiceUploadRequest;
import com.app.gadfixvendor.Models.ServiceUploadModel.ServiceUploadResponse;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityServiceUploadDetailsBinding;
import com.google.android.material.snackbar.Snackbar;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServiceUploadDetailsActivity extends BaseActivity<ActivityServiceUploadDetailsBinding> {
    private File imageFile = null;
    private GadfixApiController gadfixApiController;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = binding.getRoot();
       gadfixApiController = new GadfixApiController(this);
        binding.contentDashBoard.clickCard.setOnClickListener(v -> {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            Permissions.check(ServiceUploadDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    new ImagePicker.Builder(ServiceUploadDetailsActivity.this)
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
        binding.contentDashBoard.uploadImage.setOnClickListener(v -> {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            Permissions.check(ServiceUploadDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    new ImagePicker.Builder(ServiceUploadDetailsActivity.this)
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
        binding.contentDashBoard.next.setOnClickListener(v -> {
            if (imageFile == null){
                Snackbar snackbar = Snackbar.make(v,"Upload Service Picture",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.serviceMrp.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Service Mrp",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.serviceSellingPrice.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Service Selling Price", Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.serviceType.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Service Type",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.serviceInfo.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Service Information",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else {
                binding.contentDashBoard.tvServiceSubmit.setVisibility(View.GONE);
                binding.contentDashBoard.progressBar.setVisibility(View.VISIBLE);
                ServiceUploadRequest serviceUploadRequest = new ServiceUploadRequest();
                serviceUploadRequest.setServiceMrp(binding.contentDashBoard.serviceMrp.getText().toString());
                serviceUploadRequest.setServiceSellingPrice(binding.contentDashBoard.serviceSellingPrice.getText().toString());
                serviceUploadRequest.setServiceType(binding.contentDashBoard.serviceType.getText().toString());
                serviceUploadRequest.setServiceInfo(binding.contentDashBoard.serviceInfo.getText().toString());
                serviceUploadRequest.setServiceImage(imageFile);
                getServiceUpload(serviceUploadRequest);
            }
        });

    }

    private void getServiceUpload(ServiceUploadRequest serviceUploadRequest) {
        gadfixApiController.getUploadService(serviceUploadRequest, new AppListener.OnServiceUploadListener() {
            @Override
            public void onSuccess(ServiceUploadResponse serviceUploadResponse) {
                if (serviceUploadResponse.getResponseCode() == 200){
                    binding.contentDashBoard.tvServiceSubmit.setVisibility(View.VISIBLE);
                    binding.contentDashBoard.progressBar.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(view,serviceUploadResponse.getSuccess(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                    startActivity(new Intent(ServiceUploadDetailsActivity.this, UploadActivity.class));
                }
            }

            @Override
            public void onFailure(String message) {
                binding.contentDashBoard.tvServiceSubmit.setVisibility(View.VISIBLE);
                binding.contentDashBoard.progressBar.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(view,message,Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("mPaths", mPaths.toString());

            binding.contentDashBoard.clickCard.setVisibility(View.GONE);
            binding.contentDashBoard.uploadCard.setVisibility(View.VISIBLE);
            imageFile = new File(mPaths.get(0));
            Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            binding.contentDashBoard.uploadImage.setImageBitmap(myBitmap);
            Log.d("pathhhh", "onActivityResult: " + imageFile);


        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_service_upload_details;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_upload;
    }
}