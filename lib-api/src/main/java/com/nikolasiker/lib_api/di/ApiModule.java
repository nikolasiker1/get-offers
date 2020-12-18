package com.nikolasiker.lib_api.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikolasiker.lib_api.BuildConfig;
import com.nikolasiker.lib_api.service.OfferService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private static final long DEFAULT_TIMEOUT = 60L;

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.LOG_LEVEL))
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory
                .create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                );
    }

    @Provides
    @Singleton
    public String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory
                .create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(
            String baseUrl,
            OkHttpClient okHttpClient,
            CallAdapter.Factory callAdapterFactory,
            GsonConverterFactory gsonConverterFactory
    ) {
        return new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public OfferService provideOfferService(Retrofit retrofit) {
        return retrofit.create(OfferService.class);
    }
}
