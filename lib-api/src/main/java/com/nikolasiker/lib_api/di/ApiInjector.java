package com.nikolasiker.lib_api.di;

public class ApiInjector {
    public static ApiComponent apiComponent;

    public static void inject() {
        apiComponent = DaggerApiComponent
                .builder()
                .apiModule(new ApiModule())
                .build();
    }
}
