package com.app.gadfixvendor.Repository;

import android.content.Context;

import com.app.gadfixvendor.Interface.ApiService;
import com.app.gadfixvendor.Listener.AppListener;

public class AppRepository {
    private Context context;
    private ApiService apiService;
    private AppListener.OnUserRegisterListener onGetOfferListener;

    public AppRepository(Context context, ApiService apiService) {
        this.context = context;
        this.apiService = apiService;
    }
}
