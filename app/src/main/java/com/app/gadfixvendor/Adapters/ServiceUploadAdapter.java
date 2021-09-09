package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.Models.ServiceUploadModel.ServiceResponseData;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ServiceUploadRowBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class ServiceUploadAdapter extends RecyclerView.Adapter<ServiceUploadAdapter.uploadViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<ServiceResponseData> serviceResponseDataList;

    public ServiceUploadAdapter(Context context, List<ServiceResponseData> serviceResponseDataList) {
        this.context = context;
        this.serviceResponseDataList = serviceResponseDataList;
    }

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
        ServiceResponseData data = serviceResponseDataList.get(position);
        Glide.with(context).load(data.getServiceImg()).into(holder.binding.serviceImage);
        holder.binding.serviceType.setText(data.getServiceType());
        holder.binding.serviceInfo.setText(data.getInfo());
        holder.binding.serviceSellingPrice.setText(context.getResources().getString(R.string.Rs)+data.getSellingPrice());
        holder.binding.serviceMrp.setText("MRP"+data.getMrpPrice());
        holder.binding.serviceOff.setText(data.getOfferPercent()+" %");
        holder.binding.serviceMrp.setPaintFlags(holder.binding.serviceMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return serviceResponseDataList.size();
    }

    public class uploadViewHolder extends RecyclerView.ViewHolder{
        ServiceUploadRowBinding binding;
        public uploadViewHolder(@NonNull ServiceUploadRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
