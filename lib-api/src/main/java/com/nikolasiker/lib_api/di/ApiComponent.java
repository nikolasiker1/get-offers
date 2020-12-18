package com.nikolasiker.lib_api.di;

import com.nikolasiker.lib_api.api.CoreApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(CoreApi api);
}
