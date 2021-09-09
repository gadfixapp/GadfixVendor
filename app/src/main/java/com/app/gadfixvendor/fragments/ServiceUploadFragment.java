package com.app.gadfixvendor.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.gadfixvendor.Activities.ServiceUploadDetailsActivity;
import com.app.gadfixvendor.Adapters.ServiceUploadAdapter;
import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.ServiceUploadModel.ServiceResponse;
import com.app.gadfixvendor.Models.ServiceUploadModel.ServiceResponseData;
import com.app.gadfixvendor.Network.GadfixApiController;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentServiceUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ServiceUploadFragment extends Fragment {
    private FragmentServiceUploadBinding binding;
    private GadfixApiController gadfixApiController;
    private List<ServiceResponseData> serviceResponseDataList;
    private ServiceUploadAdapter serviceUploadAdapter;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_service_upload, container, false);
        root = binding.getRoot();
        gadfixApiController = new GadfixApiController(getActivity());


        binding.addService.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ServiceUploadDetailsActivity.class));
        });

        service();
        return root;
    }
    private void service(){
        gadfixApiController.getService(new AppListener.OnServiceListener() {
            @Override
            public void onSuccess(ServiceResponse serviceResponse) {
                if (serviceResponse.getResponseCode() == 200){
                    binding.prgressBar.setVisibility(View.GONE);
                    binding.serviceRv.setVisibility(View.VISIBLE);
                    serviceResponseDataList = serviceResponse.getData();
                    getService(serviceResponseDataList);
                    if (serviceUploadAdapter.getItemCount() == 0){
                        binding.prgressBar.setVisibility(View.GONE);
                        binding.serviceRv.setVisibility(View.GONE);
                        binding.noDataFound.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(String message) {
                Snackbar snackbar = Snackbar.make(root,message,Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void getService(List<ServiceResponseData> serviceResponseDataList) {
        binding.serviceRv.setHasFixedSize(true);
        binding.serviceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        serviceUploadAdapter = new ServiceUploadAdapter(getActivity(),serviceResponseDataList);
        binding.serviceRv.setAdapter(serviceUploadAdapter);
    }

}