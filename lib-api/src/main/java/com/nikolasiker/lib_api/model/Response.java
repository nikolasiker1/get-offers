package com.nikolasiker.lib_api.model;

import java.util.List;

public class Response {
    private String code;
    private String message;
    private int count;
    private int pages;
    private Information information;
    private List<Offer> offers;

    public Response(String code, String message, int count, int pages, Information information, List<Offer> offers) {
        this.code = code;
        this.message = message;
        this.count = count;
        this.pages = pages;
        this.information = information;
        this.offers = offers;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public Information getInformation() {
        return information;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
