package com.app.gadfixvendor.Models;

import androidx.lifecycle.ViewModel;

import com.app.gadfixvendor.Repository.AppRepository;

public class AppViewModel extends ViewModel {

    private final AppRepository appRepository;


    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }
}
