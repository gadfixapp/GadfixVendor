package com.app.gadfixvendor;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FeatchCurrentLocationAddress extends IntentService {

    private ResultReceiver resultReceiver;

    public FeatchCurrentLocationAddress() {
        super("FetchCurrentAddress");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(com.app.gadfixvendor.Constant.RECEIVER);
            Location location = intent.getParcelableExtra(com.app.gadfixvendor.Constant.LOCATION_DATA_EXTRA);
            if (location == null) {
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {

                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            } catch (Exception e) {
                errorMessage = e.getMessage();

            }
            if (addresses == null || addresses.isEmpty()) {
                deliveryResultToReceive(com.app.gadfixvendor.Constant.FAILURE_RESULT, errorMessage);
            } else {
                Address address = addresses.get(0);

                ArrayList<String> addressArrayList = new ArrayList<>();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressArrayList.add(address.getAddressLine(i));
                    Log.d("address", " " + addressArrayList);
                }
                deliveryResultToReceive(com.app.gadfixvendor.Constant.SUCCESS_RESULT, TextUtils.join(
                        Objects.requireNonNull(System.getProperty("line.separator")),
                        addressArrayList
                ));
            }
        }

    }
    private void deliveryResultToReceive(int resultcode, String addressMessage)
    {
        Bundle bundle = new Bundle();
        bundle.putString(com.app.gadfixvendor.Constant.RESULT_DATA_KEY,addressMessage);
        resultReceiver.send(resultcode,bundle);
    }
}
