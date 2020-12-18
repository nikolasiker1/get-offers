package com.nikolasiker.getoffers.application;

import android.app.Application;

import com.nikolasiker.lib_api.di.ApiInjector;

public class GetOffersApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiInjector.inject();
    }
}
