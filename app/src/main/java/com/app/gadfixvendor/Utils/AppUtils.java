package com.app.gadfixvendor.Utils;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;

import static android.content.Context.LOCATION_SERVICE;

public class AppUtils {

    public static void launchActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }


    /**
     * replace the fragment using local broadcast receiver
     *
     * @param context
     * @param code
     */
    public static void finishActivity(Context context, int code) {
        Intent intent = new Intent("finish-activity");
        intent.putExtra("code", code);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static String getUserId(Context context) {
        UserSharedpreference userSharedpreference = UserSharedpreference.getInstance(context);
        return userSharedpreference.getStringData(SharedPreferenceConfig.USER_ID);
    }

    public static boolean isGpsEnabled(Context context) {
        LocationManager service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER) && service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /**
     * get first name from string
     *
     * @param name
     * @return
     */
    public static String getFirstName(String name) {
        String firstName = "";
        if (name.split("\\w+").length > 1) {
            firstName = name.substring(0, name.lastIndexOf(' '));
        } else {
            firstName = name;
        }
        return firstName;
    }
}
