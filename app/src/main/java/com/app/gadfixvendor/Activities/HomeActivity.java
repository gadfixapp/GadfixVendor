package com.app.gadfixvendor.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gadfixvendor.Adapters.DashboardBannerAdapter;
import com.app.gadfixvendor.Constant;
import com.app.gadfixvendor.FeatchCurrentLocationAddress;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityHomeBinding;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private View view;
    private String getData,lat,log;
    private ResultReceiver resultReceiver;
    MenuItem menuItem;
    private TextView notificationCount;
    int pandingNotification = 3;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.mobilefix_image,R.drawable.mobilefix_image1,R.drawable.mobilefix_image2,R.drawable.mobilefix_image3,R.drawable.regbg};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = binding.getRoot();

        binding.contentDashBoard.tvSymbol.setText(this.getResources().getString(R.string.Rs));


        resultReceiver = new AddressResultReceiver(new Handler());
        //Google access location
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
        }
        getBanner();
        textViewCounting();

    }
    //textView with counting
    private void textViewCounting(){
        int oldValue = Integer.parseInt(binding.contentDashBoard.tvEarning.getText().toString());
        ValueAnimator animator = ValueAnimator.ofInt(oldValue, oldValue + 200);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                binding.contentDashBoard.tvEarning.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();

        int oldCompleteTask = Integer.parseInt(binding.contentDashBoard.tvCompleteTask.getText().toString());
        ValueAnimator animator1 = ValueAnimator.ofInt(oldCompleteTask, oldCompleteTask + 100);
        animator1.setDuration(2000);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                binding.contentDashBoard.tvCompleteTask.setText(animator1.getAnimatedValue().toString());
            }
        });
        animator1.start();

        int oldPendingValue = Integer.parseInt(binding.contentDashBoard.tvPendingTask.getText().toString());
        ValueAnimator animator2 = ValueAnimator.ofInt(oldPendingValue, oldPendingValue + 50);
        animator2.setDuration(2000);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                binding.contentDashBoard.tvPendingTask.setText(animator2.getAnimatedValue().toString());
            }
        });
        animator2.start();
    }
    //banner
    private void getBanner() {

        for(int i=0;i<IMAGES.length;i++){
            ImagesArray.add(IMAGES[i]);
        }


        binding.contentDashBoard.imageAutoSlider.setAdapter(new DashboardBannerAdapter(HomeActivity.this, ImagesArray));
        binding.contentDashBoard.imageAutoSlider.setClipToPadding(false);
        binding.contentDashBoard.imageAutoSlider.setClipChildren(false);
        binding.contentDashBoard.imageAutoSlider.setOffscreenPageLimit(3);
//        binding.contentDashBoard.imageAutoSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CircleIndicator indicator = (CircleIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(binding.contentDashBoard.imageAutoSlider);

        final float density = getResources().getDisplayMetrics().density;

//        indicator.setRadius(5 * density);
        indicator.setViewPager(binding.contentDashBoard.imageAutoSlider);


        NUM_PAGES = IMAGES.length;


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                binding.contentDashBoard.imageAutoSlider.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void getCurrentLocation() {
//        progressBar.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(HomeActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);

                        LocationServices.getFusedLocationProviderClient(HomeActivity.this)
                                .removeLocationUpdates(this);

                        if(locationResult != null && locationResult.getLocations().size() > 0)
                        {
                            int latestLocationIndex =  locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

//                            link = "http://maps.google.co.uk/maps?q="+latitude+","+longitude;
//                            googleLocation.setText("http://maps.google.co.uk/maps?q="+latitude+","+longitude);
//                            Linkify.addLinks(googleLocation, Linkify.WEB_URLS);

                            lat = String.valueOf(latitude);
                            log = String.valueOf(longitude);
                            Log.d("address", " " + lat);
                            Log.d("address", " " + log);
//                            Log.d("address", " " + link);
                            Location location1 = new Location("providerNA");
                            location1.setLatitude(latitude);
                            location1.setLongitude(longitude);
                            FetchAddressFromLatLog(location1);
                        }
                        else {
//                            progressBar.setVisibility(View.GONE);
                        }

                    }
                }, Looper.getMainLooper());
    }



    private void FetchAddressFromLatLog(Location location)
    {
        Intent intent = new Intent(this, FeatchCurrentLocationAddress.class);
        intent.putExtra(Constant.RECEIVER,resultReceiver);
        intent.putExtra(Constant.LOCATION_DATA_EXTRA,location);
        startService(intent);
        Log.d("sumit", " " + Constant.LOCATION_DATA_EXTRA);

    }


    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constant.SUCCESS_RESULT) {
                getData = resultData.getString(Constant.RESULT_DATA_KEY);
                binding.textdashtitle.setText(getData+" ");
                binding.textdashtitle.setSelected(true);
                Log.d("ranjan"," "+getData);

            } else {
                Toast.makeText(HomeActivity.this, resultData.getString(Constant.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
//            progressBar.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.action_notifiction);
        notificationCount = findViewById(R.id.notification_count);

        if (pandingNotification == 0){

            menuItem.setActionView(null);
        }else {
            menuItem.setActionView(R.layout.notification_bedge);
            View view = menuItem.getActionView();
            notificationCount = view.findViewById(R.id.notification_count);
            notificationCount.setText(String.valueOf(pandingNotification));


            notificationCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menuItem.setActionView(null);
                    startActivity(new Intent(HomeActivity.this,NotificationActivity.class));
                }
            });

        }
        return true;
    }

    //content view
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    // bottom navigation view
    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}