package com.app.gadfixvendor.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.app.gadfixvendor.Activities.LoginActivity;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    );

    public static void launchActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }


    /**
     * get device id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (deviceId == null) {
            return "";
        }
        return deviceId;
    }

    /**
     * email validation
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\+.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }


    /**
     * show success toast
     *
     * @param context
     * @param message
     */
    public static void showSuccessToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * show error toast
     *
     * @param context
     * @param message
     */
    public static void showToastWithErrorMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }






    /**
     * valid password length
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            return false;
        }
        return true;
    }


    /**
     * set toolbar camp_status_activity
     * @param toolbar
     * @param text
     */
//    public static void setToolbarText(Toolbar toolbar, String text, Context context){
//        toolbar.setTitle(text);
//        toolbar.setTitleTextAppearance(context, R.style.ToolbarTextApperence);
//        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white));
//    }

    /**
     * toolbar navigation
     * @param toolbar
     * @param activity
     */
    public static void toolbarNavigation(Toolbar toolbar, Activity activity){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * delete all data when user logged out
     *
     * @param context
     */
    public static void deleteSharedPreferenceDataForlogout(Context context) {
        UserSharedpreference userSharedpreference = UserSharedpreference.getInstance(context);
        userSharedpreference.deleteSharedPrefrenceMemory();
//        DbFactory.buildDb(context).clearAllTables();
        ((Activity) context).finishAffinity();
        ((Activity) context).startActivity(new Intent(context, LoginActivity.class));
    }
}
