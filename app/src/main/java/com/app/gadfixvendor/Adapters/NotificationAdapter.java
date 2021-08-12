package com.app.gadfixvendor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gadfixvendor.R;

import org.jetbrains.annotations.NotNull;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    String s1[],s2[];
    Context cnt;

    public NotificationAdapter(Context cont,String str1[],String str2[]){

        cnt=cont;
        s1=str1;
        s2=str2;
    }



    @Override
    public MyViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflaternoti=LayoutInflater.from(cnt);
        View view=layoutInflaternoti.inflate(R.layout.notification_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {

        holder.nttitle.setText(s1[position]);
        holder.ntcontent.setText(s2[position]);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nttitle,ntcontent;

        public MyViewHolder(@NotNull View itemView) {
            super(itemView);

            nttitle=itemView.findViewById(R.id.noti_title);
            ntcontent=itemView.findViewById(R.id.noti_content);
        }
    }
}
