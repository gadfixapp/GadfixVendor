package com.app.gadfixvendor.Models;

import androidx.lifecycle.ViewModel;

import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationRequest;
import com.app.gadfixvendor.Repository.AppRepository;

import javax.inject.Inject;

public class AppViewModel extends ViewModel {

    private final AppRepository appRepository;



    @Inject
    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    //registration viewModel
    public void getRegistration(RegistrationRequest registrationRequest, AppListener.OnUserRegisterListener onUserRegisterListener){
        appRepository.getRegistration(registrationRequest,onUserRegisterListener);
    }
}
