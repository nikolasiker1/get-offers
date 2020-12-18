package com.nikolasiker.feature_offer_list.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nikolasiker.lib_api.api.CoreApi;

public class OfferViewModelFactory implements ViewModelProvider.Factory {
    private CoreApi coreApi;

    public OfferViewModelFactory(CoreApi coreApi) {
        this.coreApi = coreApi;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OfferListViewModel(coreApi);
    }
}
