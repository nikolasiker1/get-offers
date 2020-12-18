package com.nikolasiker.lib_api.model;

public class Information {
    private String appName;
    private String appid;
    private String virtualCurrency;
    private String language;
    private String supportUrl;

    public Information(String appName, String appid, String virtualCurrency, String language, String supportUrl) {
        this.appName = appName;
        this.appid = appid;
        this.virtualCurrency = virtualCurrency;
        this.language = language;
        this.supportUrl = supportUrl;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppid() {
        return appid;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public String getLanguage() {
        return language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }
}
