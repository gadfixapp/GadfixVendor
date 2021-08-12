package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.gadfixvendor.Adapters.NotificationAdapter;
import com.app.gadfixvendor.Adapters.PaymentAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityNotificationBinding;

public class NotificationActivity extends BaseActivity<ActivityNotificationBinding> {

    RecyclerView recyclerView;
    String noti_title[],noti_content[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView=findViewById(R.id.recyclerViewNoti);
        noti_title=getResources().getStringArray(R.array.notification_title);
        noti_content=getResources().getStringArray(R.array.notification_content);

        NotificationAdapter Nadpt=new NotificationAdapter(this,noti_title,noti_content);
        recyclerView.setAdapter(Nadpt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //content view
    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification;
    }

    //navigation view
    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}