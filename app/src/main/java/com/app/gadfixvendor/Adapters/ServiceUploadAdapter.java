package com.app.gadfixvendor.Adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ServiceUploadRowBinding;

public class ServiceUploadAdapter extends RecyclerView.Adapter<ServiceUploadAdapter.uploadViewHolder> {
    private LayoutInflater layoutInflater;
    @NonNull
    @Override
    public ServiceUploadAdapter.uploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceUploadRowBinding binding;
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.service_upload_row,parent,false);
        return new ServiceUploadAdapter.uploadViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull uploadViewHolder holder, int position) {
        holder.binding.tvMrp.setPaintFlags(holder.binding.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class uploadViewHolder extends RecyclerView.ViewHolder{
        ServiceUploadRowBinding binding;
        public uploadViewHolder(@NonNull ServiceUploadRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
