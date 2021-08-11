package com.app.gadfixvendor.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gadfixvendor.Adapters.ProductUploadAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentProductUploadBinding;

public class ProductUploadFragment extends Fragment {
    private FragmentProductUploadBinding binding;
    private View root;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_upload, container, false);
        root = binding.getRoot();

        binding.productRv.setHasFixedSize(true);
        binding.productRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.productRv.setAdapter(new ProductUploadAdapter());
        return root;
    }
}