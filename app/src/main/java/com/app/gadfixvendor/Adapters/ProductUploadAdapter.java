package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.Models.ProductModel.ProductResponseData;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ProductUploadRowBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductUploadAdapter extends RecyclerView.Adapter<ProductUploadAdapter.uploadViewHolder> {
    private Context context;
    private List<ProductResponseData> productResponseDataList;
    private LayoutInflater layoutInflater;

    public ProductUploadAdapter(Context context, List<ProductResponseData> productResponseDataList) {
        this.context = context;
        this.productResponseDataList = productResponseDataList;
    }

    @NonNull
    @Override
    public ProductUploadAdapter.uploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductUploadRowBinding binding;
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.product_upload_row,parent,false);
        return new ProductUploadAdapter.uploadViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductUploadAdapter.uploadViewHolder holder, int position) {
        ProductResponseData data = productResponseDataList.get(position);
        Glide.with(context).load(data.getProductImg()).into(holder.binding.productImage);
        holder.binding.productName.setText(data.getProductName());
        holder.binding.productType.setText(data.getType());
        holder.binding.productSellingPrice.setText(context.getResources().getString(R.string.Rs)+data.getSellingPrice());
        holder.binding.productMrp.setText("MRP"+data.getMrpPrice());
        holder.binding.productOff.setText(data.getOfferPercent()+" %");
        holder.binding.productMrp.setPaintFlags(holder.binding.productMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return productResponseDataList.size();
    }

    public class uploadViewHolder extends RecyclerView.ViewHolder{
        ProductUploadRowBinding binding;
        public uploadViewHolder(@NonNull ProductUploadRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
