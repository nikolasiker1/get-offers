package com.nikolasiker.feature_offer_list.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nikolasiker.lib_api.api.ApiResponse;
import com.nikolasiker.lib_api.api.CoreApi;
import com.nikolasiker.lib_api.model.Offer;
import com.nikolasiker.lib_api.repository.OfferRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.disposables.CompositeDisposable;

public class OfferListViewModel extends ViewModel {
    private CoreApi coreApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<ApiResponse<List<Offer>>> offersMutableLiveData = new MutableLiveData<>();
    private LiveData<ApiResponse<List<Offer>>> offerLiveData = offersMutableLiveData;

    public OfferListViewModel(CoreApi coreApi) {
        this.coreApi = coreApi;
    }

    public void getOffers(OfferRepository.OfferParameters offerParameters) {
        offersMutableLiveData.postValue(new ApiResponse.Loading<>());
        offerParameters.setTimestamp(generateUnixTimestamp());
        try {
            offerParameters.setHashkey(generateHash(offerParameters));
        } catch (NoSuchAlgorithmException e) {
            offersMutableLiveData.postValue(new ApiResponse.Error<>());
        }
        compositeDisposable.add(
                coreApi
                        .getOfferRepository()
                        .getOffers(offerParameters)
                        .map(response -> {
                            if (response.getCode().equals(ApiResponse.ResponseStatus.STATUS_OK.status)) {
                                return new ApiResponse.Success<>(response.getOffers());
                            } else {
                                return new ApiResponse.Error<List<Offer>>();
                            }
                        })
                        .onErrorReturn(error -> new ApiResponse.Error<>())
                        .subscribe(response -> {
                            offersMutableLiveData.postValue(response);
                        })
        );
    }

    private String generateHash(OfferRepository.OfferParameters offerParameters) throws NoSuchAlgorithmException {
        Map<String, String> sortedMap = new TreeMap<>(coreApi.getOfferRepository().convertParametersToMap(offerParameters));
        StringBuilder stringHash = new StringBuilder();
        for (String parameter : sortedMap.keySet()) {
            stringHash.append(parameter).append("=").append(sortedMap.get(parameter)).append("&");
        }
        stringHash.append(offerParameters.getApiKey());

        return generateSha1(stringHash.toString());
    }

    private String generateSha1(String stringToHash) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance(SHA1_ALGORITHM);
        byte[] result = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();

        for (byte b : result) {
            sb.append(String.format(LOWERCASE_FACTOR, b));
        }

        return sb.toString();
    }

    private String generateUnixTimestamp() {
        return String.valueOf(System.currentTimeMillis() / UNIX_TIMESTAMP_FACTOR);
    }

    public LiveData<ApiResponse<List<Offer>>> getOfferLiveData() {
        return offerLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private static final long UNIX_TIMESTAMP_FACTOR = 1000L;
    private static final String SHA1_ALGORITHM = "SHA-1";
    private static final String LOWERCASE_FACTOR = "%02x";
}
