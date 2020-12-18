package com.nikolasiker.lib_api.api;

import com.nikolasiker.lib_api.di.ApiInjector;
import com.nikolasiker.lib_api.repository.OfferRepository;

import javax.inject.Inject;

public class CoreApi {
    @Inject
    protected OfferRepository offerRepository;

    public CoreApi() {
        ApiInjector.apiComponent.inject(this);
    }

    public OfferRepository getOfferRepository() {
        return offerRepository;
    }
}
