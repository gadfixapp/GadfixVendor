package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;

import org.jetbrains.annotations.NotNull;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    String s1[],s2[],s3[];
    int img[];
    Context context;

    public PaymentAdapter(Context c, String str1[], String str2[], String str3[], int imgs[]){
        s1=str1;
        s2=str2;
        s3=str3;
        context=c;
        img=imgs;

    }


   @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater=LayoutInflater.from(context);
       View v=layoutInflater.inflate(R.layout.payment_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {

        holder.shname.setText(s1[position]);
        holder.shaddress.setText(s2[position]);
        holder.shcontact.setText(s3[position]);
        holder.shimage.setImageResource(img[position]);

    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView shname,shaddress,shcontact;
        ImageView shimage;
        Button btn;

        public MyViewHolder( @NotNull View itemView) {
            super(itemView);
            shname=itemView.findViewById(R.id.shop_name);
            shaddress=itemView.findViewById(R.id.shopaddress);
            shcontact=itemView.findViewById(R.id.shopcontact);
            shimage=itemView.findViewById(R.id.img);
        }
    }
}
