package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.gadfixvendor.Adapters.NotificationAdapter;
import com.app.gadfixvendor.Adapters.PaymentAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityNotificationBinding;

public class NotificationActivity extends BaseActivity<ActivityNotificationBinding> {
    private DividerItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.contentDashBoard.notificationRecyclerView.setHasFixedSize(true);
        binding.contentDashBoard.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.divider_bg));
        binding.contentDashBoard.notificationRecyclerView.addItemDecoration(itemDecoration);
        binding.contentDashBoard.notificationRecyclerView.setAdapter(new NotificationAdapter(this));


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