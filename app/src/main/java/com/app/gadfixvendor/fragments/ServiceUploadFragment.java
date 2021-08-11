package com.app.gadfixvendor.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gadfixvendor.Adapters.ServiceUploadAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentServiceUploadBinding;

public class ServiceUploadFragment extends Fragment {
    private FragmentServiceUploadBinding binding;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_service_upload, container, false);
        root = binding.getRoot();
        binding.serviceRv.setHasFixedSize(true);
        binding.serviceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.serviceRv.setAdapter(new ServiceUploadAdapter());
        return root;
    }
}