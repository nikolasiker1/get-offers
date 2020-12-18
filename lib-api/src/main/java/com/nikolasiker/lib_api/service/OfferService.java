package com.nikolasiker.lib_api.service;

import com.nikolasiker.lib_api.model.OfferResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OfferService {
    @GET("feed/v1/offers.json")
    Single<OfferResponse> getOffers(@QueryMap Map<String, String> options);
}
