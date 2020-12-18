package com.nikolasiker.lib_api.model;

public class OfferType {
    private String payout;
    private String timeToPayout;

    public OfferType(String payout, String timeToPayout) {
        this.payout = payout;
        this.timeToPayout = timeToPayout;
    }

    public String getPayout() {
        return payout;
    }

    public String getTimeToPayout() {
        return timeToPayout;
    }
}
