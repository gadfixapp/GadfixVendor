package com.app.gadfixvendor.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.gadfixvendor.Adapters.UploadAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityUploadBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UploadActivity extends BaseActivity<ActivityUploadBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingTab();

    }
    private void settingTab() {

        binding.contentDashBoard.tabOrder.addTab(binding.contentDashBoard.tabOrder.newTab().setText("Recent Orders"));
        binding.contentDashBoard.tabOrder.addTab(binding.contentDashBoard.tabOrder.newTab().setText("Privacy Policy"));
        binding.contentDashBoard.tabOrder.setTabGravity(TabLayout.GRAVITY_FILL);
        UploadAdapter uploadAdapter = new UploadAdapter(UploadActivity.this.getSupportFragmentManager(), getLifecycle(), binding.contentDashBoard.tabOrder.getTabCount());
        binding.contentDashBoard.viewPagerOrder.setAdapter(uploadAdapter);

        new TabLayoutMediator(binding.contentDashBoard.tabOrder, binding.contentDashBoard.viewPagerOrder, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                UploadAdapter vi = (UploadAdapter) binding.contentDashBoard.viewPagerOrder.getAdapter();
                if (vi != null) {
                    tab.setText(vi.getPageTitle(position));
                }
            }
        }).attach();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_upload;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_upload;
    }
}