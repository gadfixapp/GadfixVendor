package com.app.gadfixvendor.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.Toast;

import com.app.gadfixvendor.Adapters.ProductUploadAdapter;
import com.app.gadfixvendor.MainActivity;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentProductUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
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
    int SELECT_PHOTO = 1;
    Bitmap bitmap;
    MaterialCardView clickCard,uploadCard;
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
            uploadCard = sheetView.findViewById(R.id.image_card);
            sheetView.findViewById(R.id.remove_dialog).setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
            });
            clickCard = sheetView.findViewById(R.id.click_card);
            clickCard.setOnClickListener(v1 -> {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (intent.resolveActivity(getActivity().getPackageManager()) != null){
//
//                    activityResultLauncher.launch(intent);
//                }
//                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Add Photo!");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        if (options[item].equals("Take Photo"))
//                        {
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent,SELECT_PHOTO);
////                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
////
////                                activityResultLauncher.launch(intent);
////                            }
////                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
////                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
////                            activityResultLauncher.launch(intent);
////                            startActivityForResult(intent, 1);
//
//                        }
//                        else if (options[item].equals("Choose from Gallery"))
//                        {
//
//                            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            startActivityForResult(intent,SELECT_PHOTO);
////                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
////
////                                activityResultLauncher.launch(intent);
////                            }
////                            activityResultLauncher.launch(intent);
////                            startActivityForResult(intent, 2);
//
//                        }
//                        else if (options[item].equals("Cancel")) {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                builder.show();
//                startActivityForResult(intent, 2);
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                Permissions.check(getActivity(), permissions, null, null, new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        new ImagePicker.Builder(getActivity())
                                .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                                .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                                .directory(ImagePicker.Directory.DEFAULT)
                                .extension(ImagePicker.Extension.PNG)
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

            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Bundle bundle = result.getData().getExtras();
//                     bitmap = (Bitmap)bundle.get("data");
//                    uploadCard.setVisibility(View.VISIBLE);
//                    clickCard.setVisibility(View.GONE);
//                    productImage.setImageBitmap(bitmap);
                    List<String> mPaths = result.getData().getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
                    Log.d("mPaths", mPaths.toString()+"");
                    //Your Code
                    uploadCard.setVisibility(View.VISIBLE);
                    clickCard.setVisibility(View.GONE);
                    imgFile = new File(mPaths.get(0));
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    productImage.setImageBitmap(myBitmap);
                }
            }
        });
        return root;
    }

//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//        Toast.makeText(getActivity(), "call method", Toast.LENGTH_SHORT).show();
//        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && intent.getData() != null) {
//            List<String> mPaths = intent.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
//            Log.d("mPaths", mPaths.toString());
//            //Your Code
//            uploadCard.setVisibility(View.VISIBLE);
//            clickCard.setVisibility(View.GONE);
//            imgFile = new File(mPaths.get(0));
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            productImage.setImageBitmap(myBitmap);
//        }
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("imagedata", "onActivityResult: "+ data);
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK ) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("mPaths", mPaths.toString());
            //Your Code
            uploadCard.setVisibility(View.VISIBLE);
            clickCard.setVisibility(View.GONE);
            imgFile = new File(String.valueOf(data.getData()));
            Log.d("imagfile", "onActivityResult: "+imgFile.toString());
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            productImage.setImageBitmap(myBitmap);
        }

    }


}