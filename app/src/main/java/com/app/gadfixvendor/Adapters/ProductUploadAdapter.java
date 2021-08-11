package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ProductUploadRowBinding;

public class ProductUploadAdapter extends RecyclerView.Adapter<ProductUploadAdapter.uploadViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class uploadViewHolder extends RecyclerView.ViewHolder{
        ProductUploadRowBinding binding;
        public uploadViewHolder(@NonNull ProductUploadRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
