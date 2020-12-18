package com.nikolasiker.lib_api.repository;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.nikolasiker.lib_api.model.OfferResponse;
import com.nikolasiker.lib_api.service.OfferService;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class OfferRepository {
    private OfferService offerService;
    private Gson gson;

    @Inject
    public OfferRepository(OfferService offerService, Gson gson) {
        this.offerService = offerService;
        this.gson = gson;
    }

    public Single<OfferResponse> getOffers(OfferParameters offerParameters) {
        return offerService
                .getOffers(convertParametersToMap(offerParameters))
                .subscribeOn(Schedulers.io());
    }

    public Map<String, String> convertParametersToMap(OfferParameters offerParameters) {
        return gson
                .fromJson(
                        gson.toJson(offerParameters),
                        new TypeToken<Map<String, String>>() {
                        }.getType()
                );
    }

    public static class OfferParameters {
        private String format;
        private String appid;
        private String uid;
        private String locale;
        private String timestamp;
        private String hashkey;
        private String device;
        private String ip;
        private String page;
        private String pub0;
        private String pub1;

        @SerializedName("os_version")
        private String osVersion;
        @SerializedName("apple_id_fa")
        private String appleIdfa;
        @SerializedName("apple_idfa_tracking_enabled")
        private String appleIdfaTrackingEnabled;
        @SerializedName("google_ad_id")
        private String googleAdId;
        @SerializedName("google_ad_id_limited_tracking_enabled")
        private String googleAdIdLimitedTrackingEnabled;
        @SerializedName("offer_types")
        private String offerTypes;
        @SerializedName("phone_version")
        private String phoneVersion;
        @SerializedName("gdpr_privacy_consent")
        private String gdprPrivacyConsent;
        @SerializedName("placement_id")
        private String placementId;

        private transient String apiKey;

        public OfferParameters(String format, String appid, String uid) {
            this.format = format;
            this.appid = appid;
            this.uid = uid;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getHashkey() {
            return hashkey;
        }

        public void setHashkey(String hashkey) {
            this.hashkey = hashkey;
        }

        public String getAppleIdfa() {
            return appleIdfa;
        }

        public void setAppleIdfa(String appleIdfa) {
            this.appleIdfa = appleIdfa;
        }

        public String getAppleIdfaTrackingEnabled() {
            return appleIdfaTrackingEnabled;
        }

        public void setAppleIdfaTrackingEnabled(String appleIdfaTrackingEnabled) {
            this.appleIdfaTrackingEnabled = appleIdfaTrackingEnabled;
        }

        public String getGoogleAdId() {
            return googleAdId;
        }

        public void setGoogleAdId(String googleAdId) {
            this.googleAdId = googleAdId;
        }

        public String getGoogleAdIdLimitedTrackingEnabled() {
            return googleAdIdLimitedTrackingEnabled;
        }

        public void setGoogleAdIdLimitedTrackingEnabled(String googleAdIdLimitedTrackingEnabled) {
            this.googleAdIdLimitedTrackingEnabled = googleAdIdLimitedTrackingEnabled;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getOfferTypes() {
            return offerTypes;
        }

        public void setOfferTypes(String offerTypes) {
            this.offerTypes = offerTypes;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPub0() {
            return pub0;
        }

        public void setPub0(String pub0) {
            this.pub0 = pub0;
        }

        public String getPub1() {
            return pub1;
        }

        public void setPub1(String pub1) {
            this.pub1 = pub1;
        }

        public String getPhoneVersion() {
            return phoneVersion;
        }

        public void setPhoneVersion(String phoneVersion) {
            this.phoneVersion = phoneVersion;
        }

        public String getGdprPrivacyConsent() {
            return gdprPrivacyConsent;
        }

        public void setGdprPrivacyConsent(String gdprPrivacyConsent) {
            this.gdprPrivacyConsent = gdprPrivacyConsent;
        }

        public String getPlacementId() {
            return placementId;
        }

        public void setPlacementId(String placementId) {
            this.placementId = placementId;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }
}
