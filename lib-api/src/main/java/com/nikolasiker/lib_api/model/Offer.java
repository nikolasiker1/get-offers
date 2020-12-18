package com.nikolasiker.lib_api.model;

import java.util.List;

public class Offer {
    private String link;
    private String offerId;
    private String title;
    private String teaser;
    private String requiredActions;
    private Thumbnail thumbnail;
    private List<OfferType> offerTypes;

    public Offer(String link, String offerId, String title, String teaser, String requiredActions, Thumbnail thumbnail, List<OfferType> offerTypes) {
        this.link = link;
        this.offerId = offerId;
        this.title = title;
        this.teaser = teaser;
        this.requiredActions = requiredActions;
        this.thumbnail = thumbnail;
        this.offerTypes = offerTypes;
    }

    public String getLink() {
        return link;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getTitle() {
        return title;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }
}
