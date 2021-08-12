package com.app.gadfixvendor.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.gadfixvendor.Adapters.ServiceUploadAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.FragmentServiceUploadBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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

        binding.addService.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetDialogTheme);

            View sheetView = LayoutInflater.from(getContext()).inflate(R.layout.service_bottom_dialog,container);
            sheetView.findViewById(R.id.remove_dialog).setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });

        return root;
    }
}