package com.app.gadfixvendor.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.gadfixvendor.Adapters.ProductUploadAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentProductUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ProductUploadFragment extends Fragment {
    private FragmentProductUploadBinding binding;
    private View root;
    ImageView productImage;
    private File imgFile = null;
    Bitmap bitmap;
    LinearLayout linearLayout;
    ActivityResultLauncher<Intent> activityResultLauncher;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_upload, container, false);
        root = binding.getRoot();

        binding.productRv.setHasFixedSize(true);
        binding.productRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.productRv.setAdapter(new ProductUploadAdapter());

        binding.addProducts.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetDialogTheme);

            View sheetView = LayoutInflater.from(getContext()).inflate(R.layout.product_bottom_dialog,container);
            productImage = sheetView.findViewById(R.id.upload_image);
            sheetView.findViewById(R.id.remove_dialog).setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
            });
            linearLayout = sheetView.findViewById(R.id.l_iv);
            linearLayout.setOnClickListener(v1 -> {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null){

                    activityResultLauncher.launch(intent);
                }

//                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                Permissions.check(getActivity(), permissions, null, null, new PermissionHandler() {
//                    @Override
//                    public void onGranted() {
//                        new ImagePicker.Builder(getActivity())
//                                .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
//                                .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
//                                .directory(ImagePicker.Directory.DEFAULT)
//                                .extension(ImagePicker.Extension.PNG)
//                                .scale(600, 600)
//                                .allowMultipleImages(false)
//                                .enableDebuggingMode(true)
//                                .build();
//                    }
//
//                    @Override
//                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
//                        super.onDenied(context, deniedPermissions);
//                    }
//
//                });

            });

            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                     bitmap = (Bitmap)bundle.get("data");
                     linearLayout.setVisibility(View.GONE);
                     productImage.setVisibility(View.VISIBLE);
                    productImage.setImageBitmap(bitmap);
//                    List<String> mPaths = result.getData().getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
////                    Log.d("mPaths", mPaths.toString()+"");
//                    //Your Code
//                    productImage.setVisibility(View.VISIBLE);
//                    imgFile = new File(mPaths.get(0));
//                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                    productImage.setImageBitmap(myBitmap);
                }
            }
        });
        return root;
    }

//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
//            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
//            Log.d("mPaths", mPaths.toString());
//            //Your Code
//            productImage.setVisibility(View.VISIBLE);
//            imgFile = new File(mPaths.get(0));
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            productImage.setImageBitmap(myBitmap);
//        }
//    }


//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//
//        if (requestCode == RESULT_OK) {
//            List<String> mPaths = intent.getData().getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
//            Log.d("mPaths", mPaths.toString());
//            //Your Code
//            productImage.setVisibility(View.VISIBLE);
//            imgFile = new File(mPaths.get(0));
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            productImage.setImageBitmap(myBitmap);
//        }
//    }
}