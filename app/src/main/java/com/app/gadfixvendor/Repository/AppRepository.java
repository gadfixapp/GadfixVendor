package com.app.gadfixvendor.Repository;

import android.content.Context;

import com.app.gadfixvendor.Activities.RegistrationActivity;
import com.app.gadfixvendor.Interface.ApiService;
import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationRequest;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationResponse;
import com.app.gadfixvendor.Utils.OtherConfig;
import com.app.gadfixvendor.Utils.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private Context context;
    private ApiService apiService;
    private AppListener.OnUserRegisterListener onUserRegisterListener;

    public AppRepository(Context context, ApiService apiService) {
        this.context = context;
        this.apiService = apiService;
    }

    //user_registration
    Callback<RegistrationResponse> registrationCallBack = new Callback<RegistrationResponse>() {
        @Override
        public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
            if (response.isSuccessful()) {
                onUserRegisterListener.onSuccess(response.body());
            } else {
                onUserRegisterListener.onFailure(OtherConfig.ERROR_MESSAGE);
                StringUtils.showToastWithErrorMessage(context, OtherConfig.ERROR_MESSAGE);
            }
        }

        @Override
        public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            t.printStackTrace();
            StringUtils.showToastWithErrorMessage(context, OtherConfig.ERROR_MESSAGE);
        }
    };


    public void getRegistration(RegistrationRequest registrationRequest,AppListener.OnUserRegisterListener onUserRegisterListener){
      this.onUserRegisterListener = onUserRegisterListener;
        Call<RegistrationResponse> call = apiService.getRegistration(registrationRequest);
        call.enqueue(registrationCallBack);
    }
}
