package com.app.gadfixvendor.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.app.gadfixvendor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    protected T binding;
    private View baseView;
    private ViewGroup mContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseView=getLayoutInflater().inflate(R.layout.activity_base,null,false);
        setContentView(baseView);

        binding = DataBindingUtil.inflate(getLayoutInflater(), getContentViewId(),(ViewGroup)baseView, false) ; //使用databinding获取子类布局

        mContainer=findViewById(R.id.container);
        mContainer.addView(binding.getRoot());


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }



    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, HomeActivity.class));

            }else if (itemId == R.id.navigation_payment){
                startActivity(new Intent(this, PaymentActivity.class));

            }
            else if (itemId == R.id.navigation_upload){
                startActivity(new Intent(this, UploadActivity.class));
            }
            else if (itemId == R.id.navigation_tickets) {

                startActivity(new Intent(this, TicketActivity.class));
            }
            else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            finish();
        }, 300);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifiction){
//            StringUtils.launchActivity(com.example.farmisto.Activites.BaseActivity.this,NotificationActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }



    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }
    protected abstract int getContentViewId();

    protected abstract int getNavigationMenuItemId();

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }


//    abstract int getLayoutId(); // this is to return which layout(activity) needs to display when clicked on tabs.
//
//    abstract int getBottomNavigationMenuItemId();//Which menu item selected and change the state of that menu item
}
