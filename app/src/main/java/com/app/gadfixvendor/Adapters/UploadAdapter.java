package com.app.gadfixvendor.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.gadfixvendor.fragments.ProductUploadFragment;
import com.app.gadfixvendor.fragments.ServiceUploadFragment;

public class UploadAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    public UploadAdapter(FragmentManager fm, @NonNull Lifecycle lifecycle, int NoofTabs) {
        super(fm,lifecycle);
        this.mNumOfTabs = NoofTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                ServiceUploadFragment serviceUploadFragment = new ServiceUploadFragment();
                return serviceUploadFragment;
            case 1:
                ProductUploadFragment productUploadFragment = new ProductUploadFragment();
                return productUploadFragment;
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Services";
        }else if(position==1){
            return "Products";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}
