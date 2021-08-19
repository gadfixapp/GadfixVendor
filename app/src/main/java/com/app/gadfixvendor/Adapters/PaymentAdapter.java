package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.PaymentViewBinding;

import org.jetbrains.annotations.NotNull;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater layoutInflater;
    private Onclick onclick;

    public interface Onclick{
        void getPosition(int position);
    }

    public PaymentAdapter(Context context,Onclick onclick) {
        this.context = context;
        this.onclick = onclick;
    }

    @Override
    public PaymentAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        PaymentViewBinding binding;
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.payment_view,parent,false);
        return new PaymentAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        holder.binding.root.setOnClickListener(v -> {
            onclick.getPosition(position);
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        PaymentViewBinding binding;

        public MyViewHolder( @NotNull PaymentViewBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
