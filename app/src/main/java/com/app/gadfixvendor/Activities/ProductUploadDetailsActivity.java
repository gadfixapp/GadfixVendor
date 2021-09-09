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
import com.app.gadfixvendor.Models.ProductUploadModel.ProductUploadRequest;
import com.app.gadfixvendor.Models.ProductUploadModel.ProductUploadResponse;
import com.app.gadfixvendor.Models.ProductUploadModel.ProductUploadResponseData;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityProductUploadDetailsBinding;
import com.app.gadfixvendor.fragments.ProductUploadFragment;
import com.google.android.material.snackbar.Snackbar;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductUploadDetailsActivity extends BaseActivity<ActivityProductUploadDetailsBinding>{
    private File imageFile = null;
    private GadfixApiController gadfixApiController;
    private View view;
    private List<ProductUploadResponseData> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gadfixApiController = new GadfixApiController(this);
        view = binding.getRoot();

        binding.contentDashBoard.clickCard.setOnClickListener(v -> {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            Permissions.check(ProductUploadDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    new ImagePicker.Builder(ProductUploadDetailsActivity.this)
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
            Permissions.check(ProductUploadDetailsActivity.this, permissions, null, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    new ImagePicker.Builder(ProductUploadDetailsActivity.this)
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
                Snackbar snackbar = Snackbar.make(v,"Upload Your Product Picture",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.productMrp.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Product MRP",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.productSellingPrice.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Product Selling Price",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.productName.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter Product Name",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else if (binding.contentDashBoard.productType.getText().toString().trim().equals("")){
                Snackbar snackbar = Snackbar.make(v,"Enter your Product Type",Snackbar.LENGTH_LONG);
                snackbar.show();
            }else {
                binding.contentDashBoard.tvProductSubmit.setVisibility(View.GONE);
                binding.contentDashBoard.progressBar.setVisibility(View.VISIBLE);
                ProductUploadRequest productUploadRequest = new ProductUploadRequest();
                productUploadRequest.setProductName(binding.contentDashBoard.productName.getText().toString());
                productUploadRequest.setProductMrp(binding.contentDashBoard.productMrp.getText().toString());
                productUploadRequest.setProductSellingPrice(binding.contentDashBoard.productSellingPrice.getText().toString());
                productUploadRequest.setProductType(binding.contentDashBoard.productType.getText().toString());
                productUploadRequest.setProductImage(imageFile);
                getProductUploadDetails(productUploadRequest);
            }
        });

    }

    private void getProductUploadDetails(ProductUploadRequest productUploadRequest) {

        gadfixApiController.getProductUpload(productUploadRequest, new AppListener.OnProductUploadListener() {
            @Override
            public void onSuccess(ProductUploadResponse productUploadResponse) {
                if (productUploadResponse.getResponseCode() == 200){
                    binding.contentDashBoard.tvProductSubmit.setVisibility(View.VISIBLE);
                    binding.contentDashBoard.progressBar.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(view,productUploadResponse.getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                    startActivity(new Intent(ProductUploadDetailsActivity.this, UploadActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(String message) {
                binding.contentDashBoard.tvProductSubmit.setVisibility(View.VISIBLE);
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

        return R.layout.activity_product_upload_details;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_upload;
    }


}