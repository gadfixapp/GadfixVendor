package com.app.gadfixvendor.Network;

import android.content.Context;

import androidx.databinding.library.baseAdapters.BuildConfig;

import com.app.gadfixvendor.Interface.ApiService;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL="http://biharivendor.com/admin/WebApi/";
    private static Context context;


    private static Interceptor REQUEST_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            UserSharedpreference userSharedpreference = UserSharedpreference.getInstance(context);
            request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Deviceid", userSharedpreference.getStringData(SharedPreferenceConfig.DEVICE_ID) == null ?
                            "" : userSharedpreference.getStringData(SharedPreferenceConfig.DEVICE_ID))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    };

    /**
     * get retrofit service
     *
     * @return
     */
    public static ApiService getOfferChargeAppWebService() {
        OkHttpClient.Builder client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit
                .SECONDS).readTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(5);

        client.dispatcher(dispatcher);
        client.addInterceptor(REQUEST_INTERCEPTOR);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
