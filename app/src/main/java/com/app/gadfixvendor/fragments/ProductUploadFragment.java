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

import com.app.gadfixvendor.Activities.ProductUploadDetailsActivity;
import com.app.gadfixvendor.Activities.UserDetailsActivity;
import com.app.gadfixvendor.Adapters.ProductUploadAdapter;
import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.MainActivity;
import com.app.gadfixvendor.Models.ProductModel.ProductResponse;
import com.app.gadfixvendor.Models.ProductModel.ProductResponseData;
import com.app.gadfixvendor.Models.ProductUploadModel.ProductUploadResponseData;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentProductUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
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
    private List<ProductResponseData> productResponseDataList;
    private GadfixApiController gadfixApiController;
    private ProductUploadAdapter productUploadAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_upload, container, false);
        root = binding.getRoot();

        gadfixApiController = new GadfixApiController(getActivity());







        binding.addProducts.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ProductUploadDetailsActivity.class));
        });
        product();


        return root;
    }
    private void product(){
        gadfixApiController.getProduct(new AppListener.OnProductListener() {
            @Override
            public void onSuccess(ProductResponse productResponse) {
                if (productResponse.getResponseCode() == 200){
                    binding.prgressBar.setVisibility(View.GONE);
                    binding.productRv.setVisibility(View.VISIBLE);
                    productResponseDataList = productResponse.getData();
                    getProduct(productResponseDataList);
                    if (productUploadAdapter.getItemCount() == 0){
                        binding.productRv.setVisibility(View.GONE);
                        binding.noDataFound.setVisibility(View.VISIBLE);
                    }
                }else {
                    Snackbar snackbar = Snackbar.make(root,productResponse.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(String message) {
                binding.prgressBar.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(root,message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

    }

    private void getProduct(List<ProductResponseData> productResponseDataList) {
        binding.productRv.setHasFixedSize(true);
        binding.productRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        productUploadAdapter = new ProductUploadAdapter(getActivity(),productResponseDataList);
        binding.productRv.setAdapter(productUploadAdapter);

    }

}