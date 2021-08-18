package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.NotificationViewBinding;

import org.jetbrains.annotations.NotNull;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;


    public NotificationAdapter(Context context){
        this.context = context;

    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        NotificationViewBinding binding;
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.notification_view,parent,false);
        return new NotificationAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        NotificationViewBinding binding;

        public MyViewHolder(@NotNull NotificationViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
